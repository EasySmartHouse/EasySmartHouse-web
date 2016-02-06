/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.el.expression.actuator;

import by.ginger.smarthome.el.context.EvaluationContext;
import by.ginger.smarthome.network.exception.NetworkException;
import by.ginger.smarthome.provider.device.exception.DeviceException;

/**
 *
 * @author mirash
 */
public class ActuatorOnExpression<Boolean> extends ActuatorExpression<Boolean> {

    public ActuatorOnExpression(EvaluationContext context) {
        super(context);
    }

    @Override
    public void interpret(EvaluationContext context) throws DeviceException, NetworkException {
        Boolean isOn = this.actuator.getState();
        context.addEvaluationResult(this, isOn);
    }
}
