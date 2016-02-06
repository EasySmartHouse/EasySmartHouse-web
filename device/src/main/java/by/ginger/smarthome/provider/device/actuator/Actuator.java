/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.provider.device.actuator;

import by.ginger.smarthome.provider.device.Device;
import by.ginger.smarthome.provider.device.exception.DeviceException;

/**
 *
 * @author rusakovich
 */
public interface Actuator<T> extends Device {

    /**
     * Return actuator state
     */
    public T getState() throws DeviceException;

    public void setState(T t) throws DeviceException;

    public ActuatorType getActuatorType();

}
