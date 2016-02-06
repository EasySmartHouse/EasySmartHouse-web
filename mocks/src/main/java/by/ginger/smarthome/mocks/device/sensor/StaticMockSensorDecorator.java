/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.mocks.device.sensor;

import by.ginger.smarthome.provider.device.exception.DeviceException;

/**
 *
 * @author mirash
 */
public class StaticMockSensorDecorator extends MockSensorDecorator {

    private double value;

    public StaticMockSensorDecorator(MockSensor mockSensor) {
        super(mockSensor);
    }

    @Override
    public double getValue() throws DeviceException {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
