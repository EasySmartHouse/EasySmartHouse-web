/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.el.expression;

import by.ginger.smarthome.provider.device.Device;

/**
 *
 * @author mirash
 */
public interface DeviceAware {
    
    public void setDevice(Device device);
    
}
