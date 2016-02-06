/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.el.expression.device;

import by.ginger.smarthome.el.context.EvaluationContext;
import by.ginger.smarthome.el.expression.AbstractExpression;
import by.ginger.smarthome.el.expression.DeviceAware;
import by.ginger.smarthome.provider.device.Device;

/**
 *
 * @author mirash
 */
public abstract class DeviceExpression extends AbstractExpression implements DeviceAware{

    protected Device device;

    public DeviceExpression(EvaluationContext context) {
        super(context);
    }

    @Override
    public void setDevice(Device device) {
        this.device = device;
    }
}
