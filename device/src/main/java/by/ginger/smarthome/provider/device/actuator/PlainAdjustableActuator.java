/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.provider.device.actuator;

/**
 *
 * @author mirash
 */
public class PlainAdjustableActuator extends AbstractActuator<Double> implements AdjustableActuator {

    private Double state;
    private Double minValue;
    private Double maxValue;
    private Double changeStep;
    private Double defaultValue;

    @Override
    public Double getState() {
        return state;
    }

    @Override
    public void setState(Double newState) {
        this.state = newState;
    }

    @Override
    public ActuatorType getActuatorType() {
        return ActuatorType.adjustableActuator;
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
    public Double getChangeStep() {
        return changeStep;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public void setChangeStep(Double changeStep) {
        this.changeStep = changeStep;
    }

    @Override
    public Double getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(Double defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
