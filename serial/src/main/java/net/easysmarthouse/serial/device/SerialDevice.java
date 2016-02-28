/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.serial.device;

import net.easysmarthouse.provider.device.Device;

/**
 *
 * @author mirash
 */
public interface SerialDevice extends Device {

    public void checkAvailable() throws SerialDeviceNotAvailableException;
    
    public void closeOpened();
}
