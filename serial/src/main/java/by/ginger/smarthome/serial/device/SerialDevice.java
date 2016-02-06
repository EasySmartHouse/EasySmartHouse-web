/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.serial.device;

import by.ginger.smarthome.provider.device.Device;

/**
 *
 * @author mirash
 */
public interface SerialDevice extends Device {

    public void checkAvailable() throws SerialDeviceNotAvailableException;
    
    public void closeOpened();
}
