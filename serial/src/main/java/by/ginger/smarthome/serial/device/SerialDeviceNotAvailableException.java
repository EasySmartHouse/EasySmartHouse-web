/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.serial.device;

import java.io.IOException;

/**
 *
 * @author mirash
 */
public class SerialDeviceNotAvailableException extends IOException {

    public SerialDeviceNotAvailableException() {
    }

    public SerialDeviceNotAvailableException(String message) {
        super(message);
    }

    public SerialDeviceNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public SerialDeviceNotAvailableException(Throwable cause) {
        super(cause);
    }
}
