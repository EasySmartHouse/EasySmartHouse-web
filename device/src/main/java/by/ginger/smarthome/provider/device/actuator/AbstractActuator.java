/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.provider.device.actuator;

import by.ginger.smarthome.provider.device.AbstractDevice;
import by.ginger.smarthome.provider.device.DeviceType;
import by.ginger.smarthome.provider.device.exception.DeviceException;

/**
 *
 * @author rusakovich
 */
public abstract class AbstractActuator<T> extends AbstractDevice implements Actuator<T> {

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Actuator;
    }

    @Override
    public abstract T getState() throws DeviceException;

    @Override
    public abstract void setState(T t) throws DeviceException;

}
