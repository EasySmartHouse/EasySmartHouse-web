/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.el.trigger;

import by.ginger.smarthome.el.expression.Expression;
import by.ginger.smarthome.network.exception.NetworkException;
import by.ginger.smarthome.provider.device.exception.DeviceException;
import by.ginger.smarthome.provider.device.trigger.TriggerCondition;

/**
 *
 * @author rusakovich
 */
public class ExpressionTriggerCondition implements TriggerCondition {

    private final Expression expression;

    public ExpressionTriggerCondition(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Boolean getResult() throws DeviceException {
        try {
            return Boolean.class.cast(expression.getValue());
        } catch (NetworkException ex) {
            throw new RuntimeException(ex);
        }
    }
}
