/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.el.expression.actuator;

import net.easysmarthouse.el.context.EvaluationContext;
import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.provider.device.exception.DeviceException;

/**
 *
 * @author mirash
 */
public class AdjustableValueEqualsExpression extends AdjustableValueExpression {

    public AdjustableValueEqualsExpression(EvaluationContext context) {
        super(context);
    }

    @Override
    public void interpret(EvaluationContext context) throws DeviceException, NetworkException {
        Double valueNow = this.actuator.getState();
        boolean result = (Double.compare(valueNow, this.getAdjustableValue()) == 0);
        context.addEvaluationResult(this, result);
    }
}
