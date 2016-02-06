/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.cameras.core;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamDevice;
import com.github.sarxos.webcam.WebcamLock;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author mirash
 */
public class WebcamUsbDeviceFilter extends Webcam {

    public WebcamUsbDeviceFilter(WebcamDevice device) {
        super(device);
    }

    private static boolean isLocked(Webcam webcam) {
        WebcamLock lock = webcam.getLock();
        return lock.isLocked();
    }

    public static List<Webcam> getWebcams() {
        List<Webcam> usbWebcam = new LinkedList<>();
        List<Webcam> webcams = Webcam.getWebcams();
        for (Webcam webcam : webcams) {
            if (WebcamHelper.isUsbDevice(webcam)) {

                if (isLocked(webcam)) {
                    continue;
                }

                usbWebcam.add(webcam);
            }
        }
        return usbWebcam;
    }
}
