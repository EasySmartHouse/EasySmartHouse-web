/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.el.expression;

import net.easysmarthouse.el.context.EvaluationContext;
import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.provider.device.exception.DeviceException;

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
