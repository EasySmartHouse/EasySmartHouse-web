/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.mocks.device.sensor;

import by.ginger.smarthome.mocks.device.MockDevice;
import by.ginger.smarthome.provider.device.DeviceType;
import by.ginger.smarthome.provider.device.sensor.Sensor;

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
