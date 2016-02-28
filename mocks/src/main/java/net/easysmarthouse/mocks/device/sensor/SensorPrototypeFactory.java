/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.mocks.device.sensor;

import net.easysmarthouse.provider.device.sensor.SensorType;

/**
 *
 * @author mirash
 */
interface SensorPrototypeFactory {

    public MockSensor createPrototype(SensorType sensorType);

}
