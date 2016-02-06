/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.modules;

import by.ginger.smarthome.provider.device.actuator.Actuator;
import by.ginger.smarthome.provider.device.actuator.ActuatorsModule;
import by.ginger.smarthome.provider.device.exception.DeviceException;
import by.ginger.smarthome.sheduler.task.ActuatorControlTask;
import by.ginger.smarthome.sheduler.task.TaskProperties;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rusakovich
 */
public class ActuatorControlModule extends BaseModule<Actuator> implements ActuatorsModule {

    private List<Actuator> actuators;

    public ActuatorControlModule() {
        actuators = new ArrayList<Actuator>();
    }

    @Override
    public List<Actuator> getDevices() {
        return this.actuators;
    }

    public void setActuators(List<Actuator> actuators) {
        this.actuators = actuators;
    }

    public List<Actuator> getActuators() {
        return new AbstractList<Actuator>() {
            @Override
            public boolean add(Actuator actuator) {
                if (!actuators.contains(actuator)) {
                    actuators.add(actuator);

                    scheduler.addTask(new ActuatorControlTask(actuator), new TaskProperties(true, getTaskDelay()));

                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public Actuator get(int index) {
                return actuators.get(index);
            }

            @Override
            public int size() {
                return actuators.size();
            }
        };
    }

    @Override
    public void initModule() {
        for (Actuator actuator : actuators) {
            scheduler.addTask(new ActuatorControlTask(actuator), new TaskProperties(true, this.getTaskDelay()));
        }
    }

    private Actuator findActuator(String address) {
        if (address == null) {
            throw new IllegalArgumentException("Address must not be null!");
        }

        for (Actuator actuator : actuators) {
            if (actuator.getAddress().equalsIgnoreCase(address)) {
                return actuator;
            }
        }
        return null;
    }

    @Override
    public void changeState(String address, Object state)
            throws DeviceException {
        Actuator actuator = this.findActuator(address);

        if (actuator != null) {
            actuator.setState(state);
        }
    }
}
