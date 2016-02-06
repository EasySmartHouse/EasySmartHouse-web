/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.mocks.device.sensor;

import by.ginger.smarthome.provider.device.sensor.SensorType;
import static by.ginger.smarthome.provider.device.sensor.SensorType.TemperatureSensor;

/**
 *
 * @author mirash
 */
class SensorPrototypeFactoryConcrete implements SensorPrototypeFactory {

    @Override
    public MockSensor createPrototype(SensorType sensorType) {
        switch (sensorType) {
            case TemperatureSensor:
                return new TemperatureMockSensorPrototype();
            case HumiditySensor:
                return new HumidityMockSensorPrototype();
            case PressureSensor:
                return new PressureMockSensorPrototype();
            default:
                throw new IllegalStateException("No such sensor type");

        }
    }
}
