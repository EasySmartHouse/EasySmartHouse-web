/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.el.expression.alarm;

import net.easysmarthouse.el.context.EvaluationContext;
import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.provider.device.exception.DeviceException;

/**
 *
 * @author mirash
 */
public class NotAlarmExpression extends AlarmExpression {

    public NotAlarmExpression(EvaluationContext context) {
        super(context);
    }

    @Override
    public void interpret(EvaluationContext context) throws DeviceException, NetworkException {
        Boolean isNotAlarm = !this.signaling.isAlarm();
        context.addEvaluationResult(this, isNotAlarm);
    }
}
