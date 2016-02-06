/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.mocks.device.sensor;

import by.ginger.smarthome.provider.device.exception.DeviceException;
import by.ginger.smarthome.provider.device.sensor.SensorType;

/**
 *
 * @author mirash
 */
class TemperatureMockSensorPrototype extends MockSensor {

    @Override
    public SensorType getSensorType() {
        return SensorType.TemperatureSensor;
    }

    @Override
    public double getValue() throws DeviceException {
        return 0;
    }
}
