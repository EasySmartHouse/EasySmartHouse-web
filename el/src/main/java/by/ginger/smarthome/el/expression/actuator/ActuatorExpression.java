/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.el.expression.actuator;

import by.ginger.smarthome.el.context.EvaluationContext;
import by.ginger.smarthome.el.expression.AbstractExpression;
import by.ginger.smarthome.el.expression.DeviceAware;
import by.ginger.smarthome.provider.device.Device;
import by.ginger.smarthome.provider.device.actuator.Actuator;

/**
 *
 * @author mirash
 */
public abstract class ActuatorExpression<S> extends AbstractExpression implements DeviceAware {

    protected Actuator<S> actuator;

    public ActuatorExpression(EvaluationContext context) {
        super(context);
    }

    public Actuator getActuator() {
        return actuator;
    }

    @Override
    public void setDevice(Device actuator) {
        this.actuator = (Actuator) actuator;
    }
}
