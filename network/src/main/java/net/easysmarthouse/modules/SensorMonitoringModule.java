/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.modules;

import net.easysmarthouse.provider.device.sensor.Sensor;
import net.easysmarthouse.provider.device.sensor.SensorModule;
import net.easysmarthouse.sheduler.task.MonitoringTask;
import net.easysmarthouse.sheduler.task.TaskProperties;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rusakovich
 */
public class SensorMonitoringModule extends BaseModule<Sensor> implements SensorModule {

    private List<Sensor> sensors;

    public SensorMonitoringModule() {
        sensors = new ArrayList<Sensor>();
    }

    @Override
    public List<Sensor> getDevices() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public List<Sensor> getSensors() {
        return new AbstractList<Sensor>() {
            @Override
            public boolean add(Sensor sensor) {
                if (!sensors.contains(sensor)) {
                    sensors.add(sensor);

                    scheduler.addTask(new MonitoringTask(sensor), new TaskProperties(true, getTaskDelay()));

                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public Sensor get(int index) {
                return sensors.get(index);
            }

            @Override
            public int size() {
                return sensors.size();
            }
        };
    }

    @Override
    public void initModule() {
        for (Sensor sensor : sensors) {
            scheduler.addTask(new MonitoringTask(sensor), new TaskProperties(true, this.getTaskDelay()));
        }
    }
}
