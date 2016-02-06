/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.el.expression;

import by.ginger.smarthome.el.context.EvaluationContext;
import by.ginger.smarthome.network.exception.NetworkException;
import by.ginger.smarthome.provider.device.exception.DeviceException;

/**
 *
 * @author mirash
 */
public abstract class AbstractExpression implements Expression {

    protected final EvaluationContext context;

    public AbstractExpression(EvaluationContext context) {
        this.context = context;
    }

    public abstract void interpret(EvaluationContext context)
            throws DeviceException, NetworkException;

    @Override
    public Object getValue() throws DeviceException, NetworkException {
        interpret(context);
        return context.getEvaluationResult(this);
    }
}
