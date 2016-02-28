/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.sheduler.task;

import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.network.predicate.NetworkSearchSimplePredicate;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.actuator.ActuatorType;
import net.easysmarthouse.provider.device.actuator.SwitchActuator;
import net.easysmarthouse.provider.device.alarm.SignalingElement;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author rusakovich
 */
public class UpdateSignalingElementTask extends BaseTask {

    private final Log log = LogFactory.getLog(UpdateSignalingElementTask.class);
    private SignalingElement element;

    public UpdateSignalingElementTask(SignalingElement element) {
        this.element = element;
    }

    public SignalingElement getElement() {
        return element;
    }

    public void setElement(SignalingElement element) {
        this.element = element;
    }

    private SignalingElement getSignalingElement(String address) throws NetworkException {
        List<Device> devices = networkManager.getDevices();
        for (Device device : devices) {
            if (!(device instanceof SignalingElement)) {
                continue;
            }

            if (address.equalsIgnoreCase(device.getAddress())) {
                return (SignalingElement) device;
            }
        }
        return null;
    }

    @Override
    public void execute() {
        if (log.isDebugEnabled()) {
            log.debug("update signaling element: " + element.toString());
        }
        if (!element.isEnabled()) {
            return;
        }

        boolean alarm = false;
        try {
            String address = ((Device) element).getAddress();
            final boolean labelPresent = networkManager.isDevicePresent(address);
            alarm = !labelPresent;

            if (labelPresent) {
                SignalingElement elem = getSignalingElement(address);
                if (elem != null) {
                    alarm = elem.isAlarm();
                }
            }

            if (alarm) {
                log.info("Alarm activating : " + address);
            }

        } catch (Exception e) {
            log.error("Check signaling crash.", e);
        }

        boolean isStateChanged = !(element.isAlarm() == alarm);
        if (isStateChanged) {
            element.setAlarm(alarm);

            String keyAddress = element.getKeyAddress();
            if (keyAddress != null) {
                NetworkSearchSimplePredicate predicate = new NetworkSearchSimplePredicate();
                predicate.setAddress(keyAddress);

                try {
                    Device device = this.networkManager.search(predicate).get(0);
                    if (device instanceof SwitchActuator) {
                        SwitchActuator current = (SwitchActuator) device;

                        if (current.getActuatorType() == ActuatorType.switchActuator) {
                            current.setState(alarm);
                        }
                    }
                } catch (Exception ex) {
                    log.error("Alarm key toggle error, key address: [" + keyAddress + "]", ex);
                }

            }
        }

    }
}
