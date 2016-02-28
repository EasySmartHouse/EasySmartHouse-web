/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.el.expression.actuator;

import net.easysmarthouse.el.context.EvaluationContext;
import net.easysmarthouse.el.expression.AbstractExpression;
import net.easysmarthouse.el.expression.DeviceAware;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.actuator.Actuator;

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
