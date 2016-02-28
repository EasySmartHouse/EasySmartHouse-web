/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.el.expression.device;

import net.easysmarthouse.el.context.EvaluationContext;
import net.easysmarthouse.el.expression.AbstractExpression;
import net.easysmarthouse.el.expression.DeviceAware;
import net.easysmarthouse.provider.device.Device;

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
