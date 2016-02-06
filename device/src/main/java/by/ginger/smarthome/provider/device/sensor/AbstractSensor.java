/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.ginger.smarthome.provider.device.sensor;

import by.ginger.smarthome.provider.device.AbstractDevice;
import by.ginger.smarthome.provider.device.DeviceType;

/**
 *
 * @author rusakovich
 */
public abstract class AbstractSensor extends AbstractDevice implements Sensor {

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Sensor;
    }

}
