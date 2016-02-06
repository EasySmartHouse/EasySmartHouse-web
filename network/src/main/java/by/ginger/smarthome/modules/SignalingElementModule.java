/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.modules;

import by.ginger.smarthome.provider.device.Device;
import by.ginger.smarthome.provider.device.alarm.SignalingElement;
import by.ginger.smarthome.provider.device.alarm.SignalingModule;
import by.ginger.smarthome.provider.device.exception.DeviceException;
import by.ginger.smarthome.sheduler.task.Task;
import by.ginger.smarthome.sheduler.task.TaskProperties;
import by.ginger.smarthome.sheduler.task.UpdateSignalingElementTask;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rusakovich
 */
public class SignalingElementModule extends BaseModule<SignalingElement> implements SignalingModule {

    private List<SignalingElement> elements;

    public SignalingElementModule() {
        elements = new ArrayList<SignalingElement>();
    }

    @Override
    public List<SignalingElement> getDevices() {
        return elements;
    }

    @Override
    public void initModule() {
        for (SignalingElement element : elements) {
            final Task task = new UpdateSignalingElementTask(element);
            scheduler.addTask(task, new TaskProperties(true, this.getTaskDelay()));
        }
    }

    public void setElements(List<SignalingElement> elements) {
        this.elements = elements;
    }
    
        public List<SignalingElement> getElements() {
        return new AbstractList<SignalingElement>() {
            @Override
            public boolean add(SignalingElement element) {
                if (!elements.contains(element)) {
                    elements.add(element);

                    scheduler.addTask(new UpdateSignalingElementTask(element), new TaskProperties(true, getTaskDelay()));

                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public SignalingElement get(int index) {
                return elements.get(index);
            }

            @Override
            public int size() {
                return elements.size();
            }
        };
    }

    public boolean isAlarm() {
        boolean result = false;
        for (SignalingElement element : elements) {
            result |= element.isAlarm();
        }
        return result;
    }

    @Override
    public void setEnabled(String address, Boolean state) throws DeviceException {
        for (SignalingElement signalingElement : elements) {

            if (signalingElement instanceof Device) {
                Device signalingDevice = (Device) signalingElement;

                if (signalingDevice.getAddress().equalsIgnoreCase(address)) {
                    signalingElement.setEnabled(state);
                    break;
                }
            }

        }
    }
}
