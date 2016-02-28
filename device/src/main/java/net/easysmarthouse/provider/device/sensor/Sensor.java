/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.provider.device.sensor;

import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.exception.DeviceException;

/**
 *
 * @author rusakovich
 */
public interface Sensor extends Device{

    /**
     * Return sensor value.
     */
    public double getValue() throws DeviceException;

    /**
     * Return sensor type. See {@link SensorType} for available types.
     */
    public SensorType getSensorType();
}
