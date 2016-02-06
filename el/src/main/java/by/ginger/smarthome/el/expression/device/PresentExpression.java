/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.el.expression.device;

import by.ginger.smarthome.el.context.EvaluationContext;
import by.ginger.smarthome.network.exception.NetworkException;
import by.ginger.smarthome.provider.device.exception.DeviceException;

/**
 *
 * @author mirash
 */
public class PresentExpression extends DeviceExpression {

    public PresentExpression(EvaluationContext context) {
        super(context);
    }

    @Override
    public void interpret(EvaluationContext context) throws DeviceException, NetworkException {
        if (device.getAddress() == null) {
            context.addEvaluationResult(this, false);
        }

        boolean result = context.getNetworkManager().isDevicePresent(device.getAddress());
        context.addEvaluationResult(this, result);
    }
}
