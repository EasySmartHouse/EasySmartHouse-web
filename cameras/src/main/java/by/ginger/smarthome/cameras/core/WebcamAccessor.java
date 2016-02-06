/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.cameras.core;

/**
 *
 * @author mirash
 */
public class WebcamAccessor {

    private static UsbCamDeviceProcessor instance = null;

    protected WebcamAccessor() {
    }

    private static synchronized void checkInstance() {
        if (instance == null) {
            instance = new UsbCamDeviceProcessor();
        }
    }

    public static CamDeviceHandler getCamDeviceHandler() {
        checkInstance();
        return instance;
    }

    public static CamDeviceReader getCamDeviceReader() {
        checkInstance();
        return instance;
    }
}
