/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.el.expression.logic;

import net.easysmarthouse.el.context.EvaluationContext;
import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.provider.device.exception.DeviceException;

/**
 *
 * @author mirash
 */
public class EqualsExpression extends CompoundExpression {

    public EqualsExpression(EvaluationContext context) {
        super(context);
    }

    @Override
    public void interpret(EvaluationContext context) throws DeviceException, NetworkException {
        exprOne.interpret(context);
        exprAnother.interpret(context);

        Boolean oneResult = (Boolean) context.getEvaluationResult(exprOne);
        Boolean anotherResult = (Boolean) context.getEvaluationResult(exprOne);
        context.addEvaluationResult(this, oneResult.equals(anotherResult));
    }
}
