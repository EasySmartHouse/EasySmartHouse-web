/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.el.expression.device;

import net.easysmarthouse.el.context.EvaluationContext;
import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.provider.device.exception.DeviceException;

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
