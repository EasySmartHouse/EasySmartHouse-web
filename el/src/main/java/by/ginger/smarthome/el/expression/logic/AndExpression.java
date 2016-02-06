/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.el.expression.logic;

import by.ginger.smarthome.el.context.EvaluationContext;
import by.ginger.smarthome.network.exception.NetworkException;
import by.ginger.smarthome.provider.device.exception.DeviceException;

/**
 *
 * @author mirash
 */
public class AndExpression extends CompoundExpression {

    public AndExpression(EvaluationContext context) {
        super(context);
    }

    @Override
    public void interpret(EvaluationContext context) throws DeviceException, NetworkException {
        exprOne.interpret(context);
        exprAnother.interpret(context);

        boolean oneResult = (Boolean) context.getEvaluationResult(exprOne);
        boolean anotherResult = (Boolean) context.getEvaluationResult(exprAnother);
        context.addEvaluationResult(this, oneResult && anotherResult);
    }
}
