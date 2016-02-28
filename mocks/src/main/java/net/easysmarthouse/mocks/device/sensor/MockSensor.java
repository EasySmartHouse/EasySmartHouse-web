/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.mocks.device.sensor;

import net.easysmarthouse.mocks.device.MockDevice;
import net.easysmarthouse.provider.device.DeviceType;
import net.easysmarthouse.provider.device.sensor.Sensor;

/**
 *
 * @author mirash
 */
public abstract class MockSensor extends MockDevice implements Sensor {
        
    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Sensor;
    }    
    
}
