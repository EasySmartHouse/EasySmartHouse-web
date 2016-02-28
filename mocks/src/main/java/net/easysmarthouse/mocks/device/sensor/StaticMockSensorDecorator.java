/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.mocks.device.sensor;

import net.easysmarthouse.provider.device.exception.DeviceException;

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
