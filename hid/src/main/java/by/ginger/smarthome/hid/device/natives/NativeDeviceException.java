/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.hid.device.natives;

import java.io.IOException;

/**
 *
 * @author mirash
 */
public class NativeDeviceException extends IOException {

    public NativeDeviceException() {
        super();
    }

    public NativeDeviceException(String message) {
        super(message);
    }

    public NativeDeviceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NativeDeviceException(Throwable cause) {
        super(cause);
    }
}
