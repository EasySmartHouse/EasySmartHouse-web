/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.device.decorators;

import net.easysmarthouse.provider.device.Device;
import static net.easysmarthouse.provider.device.DeviceType.Sensor;
import net.easysmarthouse.provider.device.exception.DeviceException;
import net.easysmarthouse.provider.device.sensor.PlainSensor;
import net.easysmarthouse.provider.device.sensor.Sensor;
import net.easysmarthouse.scripting.ScriptSource;
import net.easysmarthouse.scripting.ScriptSourceFactory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rusakovich
 */
public class ScriptDeviceDecoratorProxyFactoryTest {

    public ScriptDeviceDecoratorProxyFactoryTest() {
    }

    /**
     * Test of createProxy method, of class ScriptDeviceDecoratorProxyFactory.
     */
    @Test
    public void testCreateProxy() throws DeviceException {
        System.out.println("**** createProxy *****");
        ScriptSource scriptSource = ScriptSourceFactory.createScriptResource("file:src/test/resources/sensor1Decorator.js");

        PlainSensor sensor1 = new PlainSensor();
        sensor1.setValue(4.0);
        sensor1.setLabel("label");

        Device sensorProxy = ScriptDeviceDecoratorProxyFactory.getInstance().createProxy(sensor1, scriptSource);

        double expResult = 400.0;
        double result = ((Sensor) sensorProxy).getValue();
        assertEquals(expResult, result, 0.05);
    }

}
