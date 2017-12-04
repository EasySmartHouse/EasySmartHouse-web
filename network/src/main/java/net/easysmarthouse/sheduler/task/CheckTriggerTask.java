/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.sheduler.task;

import java.io.IOException;
import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.network.predicate.NetworkSearchSimplePredicate;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.actuator.Actuator;
import net.easysmarthouse.provider.device.exception.DeviceException;
import net.easysmarthouse.provider.device.trigger.ActuatorTrigger;
import net.easysmarthouse.provider.device.trigger.Trigger;
import net.easysmarthouse.provider.device.trigger.TriggerCondition;
import java.util.List;
import net.easysmarthouse.provider.device.gateway.Gateway;
import net.easysmarthouse.provider.device.trigger.GatewayTrigger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author rusakovich
 */
public class CheckTriggerTask extends BaseTask {

    private final Log log = LogFactory.getLog(CheckTriggerTask.class);
    private final Trigger trigger;
    private final TriggerCondition condition;

    public CheckTriggerTask(Trigger trigger, TriggerCondition condition) {
        this.trigger = trigger;
        this.condition = condition;
    }

    private void sendGatewayMessage(Gateway gateway, String message)
            throws IOException {
        if (gateway != null) {
            gateway.send(message);
        }
    }

    private void setActuatorValue(String address, Object value)
            throws NetworkException, DeviceException {

        NetworkSearchSimplePredicate predicate = new NetworkSearchSimplePredicate();
        predicate.setAddress(address);

        List<Device> devices = this.networkManager.search(predicate);
        for (Device device : devices) {
            ((Actuator) device).setState(value);
        }
    }

    @Override
    public void execute() {
        if (log.isDebugEnabled()) {
            log.debug("checking trigger: " + trigger.getName());
        }

        try {
            if (!trigger.isEnabled()) {
                return;
            }

            boolean result = condition.getResult();
            if (result) {
                if (trigger instanceof ActuatorTrigger) {
                    ActuatorTrigger actTrigger = (ActuatorTrigger) trigger;

                    String address = actTrigger.getActuatorAddress();
                    Object value = actTrigger.getActuatorValue();

                    setActuatorValue(address, value);
                    return;
                }

                if (trigger instanceof GatewayTrigger) {
                    GatewayTrigger gatewayTrigger = (GatewayTrigger) trigger;

                    sendGatewayMessage(gatewayTrigger.getGateway(), gatewayTrigger.getSendingMessage());
                    return;
                }
            }

        } catch (Exception ex) {
            log.error("Error while trigger checking", ex);
        }
    }
}
