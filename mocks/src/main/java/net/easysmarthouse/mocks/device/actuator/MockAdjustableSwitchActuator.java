/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.mocks.device.actuator;

import net.easysmarthouse.mocks.device.MockDevice;
import net.easysmarthouse.mocks.device.util.ValueHelper;
import net.easysmarthouse.provider.device.DeviceType;
import net.easysmarthouse.provider.device.actuator.ActuatorType;
import net.easysmarthouse.provider.device.actuator.AdjustableActuator;
import net.easysmarthouse.provider.device.exception.DeviceException;

/**
 *
 * @author mirash
 */
public class MockAdjustableSwitchActuator extends MockDevice implements AdjustableActuator {

    private Double currState;
    private Double minValue;
    private Double maxValue;
    private Double defaultValue;

    public MockAdjustableSwitchActuator(Double minValue, Double maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.defaultValue = ValueHelper.getRandom(minValue, maxValue);
        this.currState = defaultValue;
    }

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Actuator;
    }

    @Override
    public Double getMinValue() {
        return minValue;
    }

    @Override
    public Double getMaxValue() {
        return maxValue;
    }

    @Override
    public Double getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Double getChangeStep() {
        return Double.MIN_VALUE;
    }

    @Override
    public Double getState() throws DeviceException {
        return this.currState;
    }

    @Override
    public void setState(Double newState) throws DeviceException {
        this.currState = newState;
    }

    @Override
    public ActuatorType getActuatorType() {
        return ActuatorType.adjustableActuator;
    }
}
