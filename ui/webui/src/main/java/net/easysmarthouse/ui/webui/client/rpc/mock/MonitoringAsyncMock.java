/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.rpc.mock;

import net.easysmarthouse.provider.device.sensor.PlainSensor;
import net.easysmarthouse.provider.device.sensor.Sensor;
import net.easysmarthouse.provider.device.sensor.SensorType;
import net.easysmarthouse.ui.webui.client.rpc.MonitoringServiceAsync;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.LinkedList;
import java.util.List;
import com.google.gwt.user.client.Timer;

/**
 *
 * @author rusakovich
 */
public class MonitoringAsyncMock implements MonitoringServiceAsync {

    private static final int TIMER_DELAY = 1000;

    @Override
    public void getSensors(final AsyncCallback<List<Sensor>> asyncCallback) {
        final List<Sensor> sensors = new LinkedList<Sensor>();

        PlainSensor sensor1 = new PlainSensor();
        sensor1.setAddress("C2000801AC339F10");
        sensor1.setDescription("Temperature sensor outside");
        sensor1.setLabel("Temperature sensor 1");
        sensor1.setSensorType(SensorType.TemperatureSensor);
        sensor1.setValue(-1.0d);
        sensors.add(sensor1);

        PlainSensor sensor2 = new PlainSensor();
        sensor2.setAddress("EC000801AC673410");
        sensor2.setDescription("Temperature sensor inside");
        sensor2.setLabel("Temperature sensor 2");
        sensor2.setSensorType(SensorType.TemperatureSensor);
        sensor2.setValue(25.3d);
         sensors.add(sensor2);

        final Timer timer = new Timer() {
            @Override
            public void run() {
                asyncCallback.onSuccess(sensors);
            }
        };
        timer.schedule(TIMER_DELAY);
    }

}
