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
public class NullExpression extends LogicExpression {

    public NullExpression(EvaluationContext context) {
        super(context);
    }

    @Override
    public void interpret(EvaluationContext context) throws DeviceException, NetworkException {
    }

    @Override
    public Object getValue() throws DeviceException, NetworkException {
        return null;
    }
}
