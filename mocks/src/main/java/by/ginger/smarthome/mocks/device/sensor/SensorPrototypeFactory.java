/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.mocks.device.sensor;

import by.ginger.smarthome.provider.device.sensor.SensorType;

/**
 *
 * @author mirash
 */
interface SensorPrototypeFactory {

    public MockSensor createPrototype(SensorType sensorType);

}
