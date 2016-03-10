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
import net.easysmarthouse.scripting.device.ScriptableDevicePrototype;
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
        ScriptableDevicePrototype prototype = new ScriptableDevicePrototype();
        prototype.bind(scriptSource);
        ScriptableSensor instance = new ScriptableSensor(prototype);
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
        ScriptableDevicePrototype prototype = new ScriptableDevicePrototype();
        prototype.bind(scriptSource);
        ScriptableSensor instance = new ScriptableSensor(prototype);
        SensorType sensorType = instance.getSensorType();
        assertEquals(SensorType.HumiditySensor, sensorType);
    }

    /**
     * Test of getAddress method, of class ScriptableSensor.
     */
    @Test
    public void testGetAddress() {
        System.out.println("***** getAddress *****");
        ScriptableDevicePrototype prototype = new ScriptableDevicePrototype();
        prototype.bind(scriptSource);
        ScriptableSensor instance = new ScriptableSensor(prototype);
        String address = instance.getAddress();
        assertEquals("address", address);
    }

    /**
     * Test of getLabel method, of class ScriptableSensor.
     */
    @Test
    public void testGetLabel() {
        System.out.println("***** getLabel *****");
        ScriptableDevicePrototype prototype = new ScriptableDevicePrototype();
        prototype.bind(scriptSource);
        ScriptableSensor instance = new ScriptableSensor(prototype);
        String label = instance.getLabel();
        assertEquals("label", label);
    }

    /**
     * Test of getDescription method, of class ScriptableSensor.
     */
    @Test
    public void testGetDescription() {
        System.out.println("***** getDescription *****");
        ScriptableDevicePrototype prototype = new ScriptableDevicePrototype();
        prototype.bind(scriptSource);
        ScriptableSensor instance = new ScriptableSensor(prototype);
        String description = instance.getDescription();
        assertEquals("description", description);
    }

    /**
     * Test of getDeviceType method, of class ScriptableSensor.
     */
    @Test
    public void testGetDeviceType() {
        System.out.println("***** getDeviceType *****");
        ScriptableDevicePrototype prototype = new ScriptableDevicePrototype();
        prototype.bind(scriptSource);
        ScriptableSensor instance = new ScriptableSensor(prototype);
        DeviceType deviceType = instance.getDeviceType();
        assertEquals(DeviceType.Sensor, deviceType);
    }

}
