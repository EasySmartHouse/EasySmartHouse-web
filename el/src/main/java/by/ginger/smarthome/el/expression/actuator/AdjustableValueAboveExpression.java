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
public class AdjustableValueAboveExpression extends AdjustableValueExpression {

    public AdjustableValueAboveExpression(EvaluationContext context) {
        super(context);
    }

    @Override
    public void interpret(EvaluationContext context) throws DeviceException, NetworkException {
        Double valueNow = this.actuator.getState();
        boolean result = (Double.compare(valueNow, this.getAdjustableValue()) > 0);
        context.addEvaluationResult(this, result);
    }
}
