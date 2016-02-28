/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.easysmarthouse.el.expression.sensor;

import net.easysmarthouse.el.context.EvaluationContext;
import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.provider.device.exception.DeviceException;

/**
 *
 * @author rusakovich
 */
public class ValueBelowOrEqualsExpression extends SensorExpression {

    public ValueBelowOrEqualsExpression(EvaluationContext context) {
        super(context);
    }

    @Override
    public void interpret(EvaluationContext context) throws DeviceException, NetworkException {
        double valueNow = this.sensor.getValue();

        boolean result = (Double.compare(valueNow, value) <= 0);
        context.addEvaluationResult(this, result);
    }
}
