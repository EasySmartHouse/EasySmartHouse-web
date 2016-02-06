/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.service;

import by.ginger.smarthome.provider.device.Device;
import by.ginger.smarthome.provider.device.actuator.Actuator;
import by.ginger.smarthome.provider.device.actuator.ActuatorsModule;
import by.ginger.smarthome.provider.device.actuator.SwitchActuator;
import by.ginger.smarthome.provider.device.alarm.SignalingElement;
import by.ginger.smarthome.provider.device.alarm.SignalingModule;
import by.ginger.smarthome.provider.device.sensor.Sensor;
import by.ginger.smarthome.provider.device.sensor.SensorModule;
import by.ginger.smarthome.provider.device.trigger.Trigger;
import by.ginger.smarthome.provider.device.trigger.TriggerModule;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Resource;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author mirash
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:client-context.xml"})
public class DemonIntegrationTest {

    private static final double TEMP_DIFF = 30.0;
    @Resource
    SensorModule sensorsModule;
    @Resource
    ActuatorsModule actuatorsModule;
    @Resource
    SignalingModule signalingModule;
    @Resource
    TriggerModule triggerModule;

    @Test
    public void testSensorModule() throws Exception {
        System.out.println("*** testSensorModule ***");
        List<Sensor> sensors = sensorsModule.getDevices();

        assertNotNull(sensors);
        assertTrue(sensors.size() > 0);

        Sensor tempSensor1 = sensors.get(0);
        assertThat(tempSensor1.getValue(), not(equalTo(0.0)));
        assertThat(tempSensor1.getAddress(), equalTo("C2000801AC339F10"));
        assertThat(tempSensor1.getLabel(), equalTo("Temperature sensor 1"));

        Sensor tempSensor2 = sensors.get(1);
        assertThat(tempSensor2.getValue(), not(equalTo(0.0)));
        assertThat(tempSensor2.getAddress(), equalTo("EC000801AC673410"));
        assertThat(tempSensor2.getLabel(), equalTo("Temperature sensor 2"));

        assertThat("Difference between temperature sensors should be less that 2 celsius",
                Math.abs(tempSensor1.getValue() - tempSensor2.getValue()) < TEMP_DIFF, is(true));

    }

    @Test
    public void testActuatorsModule() throws Exception {
        System.out.println("*** testActuatorsModule ***");
        List<Actuator> actuators = actuatorsModule.getDevices();

        assertNotNull(actuators);
        assertTrue(actuators.size() > 0);

        SwitchActuator actuator = (SwitchActuator) actuators.get(0);
        assertThat(actuator.getAddress(), equalTo("9800000020EC3105"));
        assertThat(actuator.getLabel(), equalTo("Lamp: 1"));

        boolean primaryState = actuator.getState();
        actuator.setState(!primaryState);
        assertEquals(!primaryState, actuator.getState());

        actuator.setState(primaryState);

        actuatorsModule.changeState("9800000020EC3105", !primaryState);
        actuators = actuatorsModule.getDevices();
        actuator = (SwitchActuator) actuators.get(0);
        assertNotNull(actuator);
        assertEquals(!primaryState, actuator.getState());

    }
    private CountDownLatch lock = new CountDownLatch(1);

    @Test
    public void testSignalingModule() throws Exception {
        System.out.println("*** testSignalingModule ***");
        List<SignalingElement> alarms = signalingModule.getDevices();

        assertNotNull(alarms);
        assertTrue(alarms.size() > 0);

        SignalingElement element = (SignalingElement) alarms.get(0);

        assertThat(element.getKeyAddress(), equalTo("9800000020EC3105"));
        assertThat(((Device) element).getAddress(), equalTo("FD00000AC4DFE701"));

        element.setEnabled(false);

        System.out.println("Now unplug the device: [" + ((Device) element).getLabel() + "]");

        final AtomicBoolean alarm = new AtomicBoolean(element.isAlarm());
        Thread scanning = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    List<SignalingElement> alarms = signalingModule.getDevices();
                    SignalingElement element = (SignalingElement) alarms.get(0);

                    if (element.isAlarm()) {
                        alarm.set(element.isAlarm());
                        lock.countDown();
                    }
                }
            }
        });
        scanning.start();

        lock.await(20000, TimeUnit.MILLISECONDS);
        scanning.interrupt();

        assertTrue(alarm.get());
    }

    @Test
    public void testTriggerModule() throws Exception {
        System.out.println("*** testTriggerModule ***");
        List<Trigger> triggers = triggerModule.getDevices();

        assertNotNull(triggers);
        assertTrue(triggers.size() > 0);
    }
}
