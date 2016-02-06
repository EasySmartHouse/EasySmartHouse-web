/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.modules;

import by.ginger.smarthome.provider.device.exception.DeviceException;
import by.ginger.smarthome.provider.device.trigger.Trigger;
import by.ginger.smarthome.provider.device.trigger.TriggerCondition;
import by.ginger.smarthome.provider.device.trigger.TriggerModule;
import by.ginger.smarthome.sheduler.task.CheckTriggerTask;
import by.ginger.smarthome.sheduler.task.Task;
import by.ginger.smarthome.sheduler.task.TaskProperties;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rusakovich
 */
public class TriggerCheckModule extends BaseModule<Trigger> implements TriggerModule {

    private Map<Trigger, TriggerCondition> triggers;

    @Override
    public List<Trigger> getDevices() {
        return new LinkedList<Trigger>(triggers.keySet());
    }

    @Override
    public void initModule() {
        for (Trigger trigger : triggers.keySet()) {
            final Task task = new CheckTriggerTask(trigger, triggers.get(trigger));
            scheduler.addTask(task, new TaskProperties(true, this.getTaskDelay()));
        }
    }

    public void setTriggers(Map<Trigger, TriggerCondition> triggers) {
        this.triggers = triggers;
    }

    @Override
    public List<Trigger> getElements() {
        return new LinkedList<Trigger>(triggers.keySet());
    }

    @Override
    public void setEnabled(String name, Boolean state) throws DeviceException {
        if (name == null) {
            return;
        }

        for (Trigger trigger : triggers.keySet()) {
            if (name.equalsIgnoreCase(trigger.getName())) {
                trigger.setEnabled(state);
                break;
            }
        }
    }
}
