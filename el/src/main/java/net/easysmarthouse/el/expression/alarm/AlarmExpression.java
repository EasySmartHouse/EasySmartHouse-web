/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.el.expression.alarm;

import net.easysmarthouse.el.context.EvaluationContext;
import net.easysmarthouse.el.expression.AbstractExpression;
import net.easysmarthouse.el.expression.DeviceAware;
import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.alarm.SignalingElement;
import net.easysmarthouse.provider.device.exception.DeviceException;

/**
 *
 * @author mirash
 */
public class AlarmExpression extends AbstractExpression implements DeviceAware {

    protected SignalingElement signaling;

    public AlarmExpression(EvaluationContext context) {
        super(context);
    }

    @Override
    public void interpret(EvaluationContext context) throws DeviceException, NetworkException {
        Boolean isAlarm = this.signaling.isAlarm();
        context.addEvaluationResult(this, isAlarm);
    }

    @Override
    public void setDevice(Device device) {
        this.signaling = (SignalingElement) device;
    }
}
