/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.device.sensor;

import javax.script.ScriptException;
import net.easysmarthouse.provider.device.DeviceType;
import net.easysmarthouse.provider.device.sensor.SensorType;
import net.easysmarthouse.scripting.ScriptSource;
import net.easysmarthouse.scripting.ScriptSourceFactory;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rusakovich
 */
public class ScriptableSensorTest {

    private ScriptSource scriptSource;

    public ScriptableSensorTest() {
    }

    @Before
    public void setUp() throws ScriptException {
        this.scriptSource = ScriptSourceFactory.createScriptResource("file:src/test/resources/sensor1.js");
    }

    /**
     * Test of getValue method, of class ScriptableSensor.
     */
    @Test
    public void testGetValue() throws Exception {
        System.out.println("**** getValue *****");
        ScriptableSensor instance = new ScriptableSensor("sensor1");
        instance.bind(scriptSource);
        double expResult = 9.99;
        double result = instance.getValue();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getSensorType method, of class ScriptableSensor.
     */
    @Test
    public void testGetSensorType() {
        System.out.println("***** getSensorType ******");
        ScriptableSensor instance = new ScriptableSensor("sensor1");
        instance.bind(scriptSource);
        SensorType sensorType = instance.getSensorType();
        assertEquals(SensorType.HumiditySensor, sensorType);
    }

    /**
     * Test of getAddress method, of class ScriptableSensor.
     */
    @Test
    public void testGetAddress() {
        System.out.println("***** getAddress *****");
        ScriptableSensor instance = new ScriptableSensor("sensor1");
        instance.bind(scriptSource);
        String address = instance.getAddress();
        assertEquals("address", address);
    }

    /**
     * Test of getLabel method, of class ScriptableSensor.
     */
    @Test
    public void testGetLabel() {
        System.out.println("***** getLabel *****");
        ScriptableSensor instance = new ScriptableSensor("sensor1");
        instance.bind(scriptSource);
        String label = instance.getLabel();
        assertEquals("label", label);
    }

    /**
     * Test of getDescription method, of class ScriptableSensor.
     */
    @Test
    public void testGetDescription() {
        System.out.println("***** getDescription *****");
        ScriptableSensor instance = new ScriptableSensor("sensor1");
        instance.bind(scriptSource);
        String description = instance.getDescription();
        assertEquals("description", description);
    }

    /**
     * Test of getDeviceType method, of class ScriptableSensor.
     */
    @Test
    public void testGetDeviceType() {
        System.out.println("***** getDeviceType *****");
        ScriptableSensor instance = new ScriptableSensor("sensor1");
        instance.bind(scriptSource);
        DeviceType deviceType = instance.getDeviceType();
        assertEquals(DeviceType.Sensor, deviceType);
    }

}
