/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.provider.device.actuator;

import net.easysmarthouse.provider.device.AbstractDevice;
import net.easysmarthouse.provider.device.DeviceType;
import net.easysmarthouse.provider.device.exception.DeviceException;

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
