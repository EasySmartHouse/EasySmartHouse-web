/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.sheduler.task;

import by.ginger.smarthome.network.predicate.NetworkSearchSimplePredicate;
import by.ginger.smarthome.provider.device.Device;
import by.ginger.smarthome.provider.device.actuator.Actuator;
import by.ginger.smarthome.provider.device.actuator.ActuatorType;
import by.ginger.smarthome.provider.device.actuator.PlainAdjustableActuator;
import by.ginger.smarthome.provider.device.actuator.SimpleSwitch;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author rusakovich
 */
public class ActuatorControlTask extends BaseTask {

    private final Actuator actuator;
    private final Log log = LogFactory.getLog(ActuatorControlTask.class);

    public ActuatorControlTask(Actuator actuator) {
        this.actuator = actuator;
    }

    @Override
    public void execute() {
        try {
            final NetworkSearchSimplePredicate predicate = new NetworkSearchSimplePredicate();
            predicate.setAddress(actuator.getAddress());

            List<Device> devices = this.networkManager.search(predicate);
            if (devices == null || devices.isEmpty()) {
                throw new RuntimeException("Cannot find suitable devices for [" + actuator.getAddress() + "]");
            }

            Device device = devices.get(0);

            log.info("Task for keys running");

            if (device instanceof Actuator) {
                Actuator current = (Actuator) device;

                log.info("Actuator found: "
                        + actuator.getAddress());

                if (current.getActuatorType() == ActuatorType.switchActuator) {
                    if (actuator instanceof SimpleSwitch) {
                        SimpleSwitch plain = (SimpleSwitch) actuator;

                        boolean currentState = (Boolean) current.getState();
                        boolean plainState = plain.getState();
                        if (currentState != plainState) {
                            current.setState(plainState);
                        }

                    } else {
                        throw new RuntimeException(actuator.getAddress() + " device is a switch!");
                    }
                } else if (current.getActuatorType() == ActuatorType.adjustableActuator) {
                    if (actuator instanceof PlainAdjustableActuator) {
                        PlainAdjustableActuator plain = (PlainAdjustableActuator) actuator;

                        log.info("Adjustable actuator found: " + actuator.getAddress());

                        if (plain.getDefaultValue() == null) {
                            plain.setDefaultValue((Double) current.getState());
                        }

                        Double plainState = plain.getState();
                        if (plainState == null) {
                            plain.setState((Double) current.getState());
                            return;
                        }
                        
                        Double changeStep = plain.getChangeStep();
                        Double actualState = (Double) current.getState();

                        if (Math.abs(actualState - plainState) >= changeStep) {
                            log.info("Adjustable actuator update, from [" + actualState + "] to "
                                    + "new value [" + plainState + "]");
                            current.setState(plainState);
                        }

                    } else {
                        throw new RuntimeException("[" + actuator.getAddress() + "] device is unknown switch!");
                    }
                }
            }
        } catch (Exception ex) {
            log.error(ex);
        }
    }
}
