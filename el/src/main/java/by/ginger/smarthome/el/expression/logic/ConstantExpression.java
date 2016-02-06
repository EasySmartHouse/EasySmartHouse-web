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
public class ConstantExpression extends LogicExpression {

    private Object value;

    public ConstantExpression(EvaluationContext context) {
        super(context);
    }

    @Override
    public void interpret(EvaluationContext context) throws DeviceException, NetworkException {
        context.addEvaluationResult(this, value);
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
