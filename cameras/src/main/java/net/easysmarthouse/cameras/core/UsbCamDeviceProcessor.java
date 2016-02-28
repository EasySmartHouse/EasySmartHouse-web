/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.cameras.core;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamDiscoveryEvent;
import com.github.sarxos.webcam.WebcamDiscoveryListener;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mirash
 */
class UsbCamDeviceProcessor implements CamDeviceReader, CamDeviceHandler, WebcamDiscoveryListener {

    private static final Logger log = Logger.getLogger(UsbCamDeviceProcessor.class.getName());
    private static final Dimension IMAGE_DIMENSION = new Dimension(640, 480);
    private static final long IMAGE_CACHE_UPDATE_PERIOD = 500;
    private ConcurrentMap<Integer, BufferedImage> imageCache = new ConcurrentHashMap<>();
    private ConcurrentMap<Integer, Webcam> devicesCache = new ConcurrentHashMap<>();
    private Timer imageCacheUpdater;

    private class DevicesReadTimerTask extends TimerTask {

        @Override
        public void run() {
            Collection<Webcam> devices = devicesCache.values();
            for (Webcam webcam : devices) {
                synchronized (webcam) {
                    int dn = WebcamHelper.getDeviceNumber(webcam);

                    if (webcam.isOpen()) {
                        imageCache.put(dn, webcam.getImage());
                    } else {
                        imageCache.remove(dn);
                    }

                }
            }
        }
    }

    @Override
    public Collection<Integer> getDeviceNumbers() {
        return devicesCache.keySet();
    }

    private void init(Webcam webcam) {
        webcam.setViewSize(IMAGE_DIMENSION);
    }

    @Override
    public BufferedImage getImage(int deviceNumber)
            throws WebcamNotAvailableException {
        if (imageCache.containsKey(deviceNumber)) {
            return imageCache.get(deviceNumber);
        }
        throw new WebcamNotAvailableException();
    }

    @Override
    public void openAvailable() {
        List<Webcam> webcams = WebcamUsbDeviceFilter.getWebcams();
        for (Webcam webcam : webcams) {
            synchronized (webcam) {
                try {
                    
                    if (webcam.getLock().isLocked()) {
                        continue;
                    }

                    init(webcam);
                    webcam.open();
                    devicesCache.putIfAbsent(WebcamHelper.getDeviceNumber(webcam), webcam);
                } catch (Throwable thr) {
                    log.log(Level.SEVERE, "Error while open the device [" + webcam.getDevice().getName() + "]", thr);
                    continue;
                }
            }
        }
        this.imageCacheUpdater = new Timer();
        imageCacheUpdater.schedule(new DevicesReadTimerTask(), 0, IMAGE_CACHE_UPDATE_PERIOD);
    }

    @Override
    public void closeAll() {
        if (imageCacheUpdater != null) {
            imageCacheUpdater.purge();
            imageCacheUpdater.cancel();
        }

        Collection<Webcam> devices = devicesCache.values();
        for (Webcam webcam : devices) {
            synchronized (webcam) {
                webcam.close();
            }
        }
    }

    private boolean inCache(Webcam webcam) {
        Collection<Webcam> devices = devicesCache.values();
        for (Webcam device : devices) {
            if (device.getName().equals(webcam.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void webcamFound(WebcamDiscoveryEvent wde) {
        Webcam found = wde.getWebcam();
        synchronized (found) {
            if (WebcamHelper.isUsbDevice(found) && !inCache(found)) {
                init(found);
                found.open();
                devicesCache.putIfAbsent(WebcamHelper.getDeviceNumber(found), found);
            }
        }
    }

    @Override
    public void webcamGone(WebcamDiscoveryEvent wde) {
        Webcam gone = wde.getWebcam();
        synchronized (gone) {
            if (inCache(gone)) {
                int dn = WebcamHelper.getDeviceNumber(gone);
                devicesCache.remove(dn);
                imageCache.remove(dn);
            }
            gone.close();
        }
    }
}
