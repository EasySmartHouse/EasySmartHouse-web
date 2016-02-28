/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.cameras.device.alarm;

import net.easysmarthouse.cameras.device.CameraDevice;
import net.easysmarthouse.provider.device.AbstractDevice;
import net.easysmarthouse.provider.device.Closeable;
import net.easysmarthouse.provider.device.DeviceType;
import net.easysmarthouse.provider.device.alarm.SignalingElement;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;

/**
 *
 * @author mirash
 */
public class MotionDetectorElement extends AbstractDevice implements SignalingElement, CameraDevice, Closeable {

    private static final int THRESHOLD_DEFAULT = 50;
    private static final int INERTIA_DEFAULT = 30;
    private static final int INTERVAL_MS = 3000;
    private WebcamMotionDetector detector;
    private final Webcam webcam;
    private int threshold = THRESHOLD_DEFAULT;
    private int inertia = INERTIA_DEFAULT;
    private int interval = INTERVAL_MS;
    private volatile boolean enabled = true;

    public MotionDetectorElement(Webcam webcam) {
        this.webcam = webcam;
        init();
    }

    private void init() {
        this.detector = new WebcamMotionDetector(webcam, threshold, inertia);
        detector.setInterval(interval);
        detector.start();
    }

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Key;
    }

    @Override
    public String getKeyAddress() {
        return null;
    }

    @Override
    public String getAddress() {
        return webcam.getName();
    }

    @Override
    public synchronized boolean isAlarm() {
        if (!enabled) {
            return false;
        }

        synchronized (webcam) {
            return detector.isMotion();
        }
    }

    @Override
    public void setAlarm(boolean enabled) {
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public synchronized void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public void close() {
        synchronized (webcam) {
            if (detector != null) {
                detector.stop();
            }
        }
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public void setInertia(int inertia) {
        this.inertia = inertia;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
