/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.el.expression.sensor;

import by.ginger.smarthome.el.context.EvaluationContext;
import by.ginger.smarthome.el.expression.AbstractExpression;
import by.ginger.smarthome.el.expression.DeviceAware;
import by.ginger.smarthome.el.expression.ValueAware;
import by.ginger.smarthome.provider.device.Device;
import by.ginger.smarthome.provider.device.sensor.Sensor;

/**
 *
 * @author mirash
 */
public abstract class SensorExpression extends AbstractExpression implements DeviceAware, ValueAware<Double>{

    protected Sensor sensor;
    protected double value;

    public SensorExpression(EvaluationContext context) {
        super(context);
    }

    public Sensor getSensor() {
        return sensor;
    }

    @Override
    public void setDevice(Device device) {
        this.sensor = (Sensor)device;
    }

    public double getSensorValue() {
        return value;
    }

    @Override
    public void setValue(Double value) {
        this.value = value;
    }
}
