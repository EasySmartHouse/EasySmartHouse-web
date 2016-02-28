/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.el.trigger;

import net.easysmarthouse.el.expression.Expression;
import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.provider.device.exception.DeviceException;
import net.easysmarthouse.provider.device.trigger.TriggerCondition;

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
