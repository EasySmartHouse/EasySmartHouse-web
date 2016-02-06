/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.el.expression.alarm;

import by.ginger.smarthome.el.context.EvaluationContext;
import by.ginger.smarthome.el.expression.AbstractExpression;
import by.ginger.smarthome.el.expression.DeviceAware;
import by.ginger.smarthome.network.exception.NetworkException;
import by.ginger.smarthome.provider.device.Device;
import by.ginger.smarthome.provider.device.alarm.SignalingElement;
import by.ginger.smarthome.provider.device.exception.DeviceException;

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
