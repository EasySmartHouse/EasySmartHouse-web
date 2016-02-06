/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.el.expression.sensor;

import by.ginger.smarthome.el.context.EvaluationContext;
import by.ginger.smarthome.network.exception.NetworkException;
import by.ginger.smarthome.provider.device.exception.DeviceException;

/**
 *
 * @author mirash
 */
public class ValueAboveExpression extends SensorExpression {

    public ValueAboveExpression(EvaluationContext context) {
        super(context);
    }

    @Override
    public void interpret(EvaluationContext context) throws DeviceException, NetworkException {
        double valueNow = this.sensor.getValue();

        boolean result = (Double.compare(valueNow, value) > 0);
        context.addEvaluationResult(this, result);
    }
}
