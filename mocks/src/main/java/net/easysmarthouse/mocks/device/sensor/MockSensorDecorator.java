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
abstract class MockSensorDecorator extends MockSensor {

    protected final MockSensor mockSensor;

    MockSensorDecorator(MockSensor mockSensor) {
        this.mockSensor = mockSensor;
    }

    @Override
    public void setLabel(String label) {
        mockSensor.setLabel(label);
    }

    @Override
    public void setDescription(String description) {
        mockSensor.setDescription(description);
    }

    @Override
    public void setAddress(String address) {
        mockSensor.setAddress(address);
    }

    @Override
    public SensorType getSensorType() {
        return mockSensor.getSensorType();
    }

    @Override
    public String getAddress() {
        return mockSensor.getAddress();
    }

    @Override
    public String getDescription() {
        return mockSensor.getDescription();
    }

    @Override
    public String getLabel() {
        return mockSensor.getLabel();
    }
}
