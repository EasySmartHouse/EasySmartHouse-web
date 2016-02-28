/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.mocks.device;

import net.easysmarthouse.provider.device.Device;

/**
 *
 * @author mirash
 */
public interface DeviceObservable {

    public void addDevice(Device device);

    public void removeDevice(Device device);
    
}
