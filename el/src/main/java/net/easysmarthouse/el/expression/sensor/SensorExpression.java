/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.el.expression.sensor;

import net.easysmarthouse.el.context.EvaluationContext;
import net.easysmarthouse.el.expression.AbstractExpression;
import net.easysmarthouse.el.expression.DeviceAware;
import net.easysmarthouse.el.expression.ValueAware;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.sensor.Sensor;

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
