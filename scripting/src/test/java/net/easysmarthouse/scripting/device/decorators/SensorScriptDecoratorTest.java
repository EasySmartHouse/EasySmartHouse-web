/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.device.decorators;

import javax.script.ScriptException;
import net.easysmarthouse.provider.device.sensor.PlainSensor;
import net.easysmarthouse.scripting.ScriptSource;
import net.easysmarthouse.scripting.ScriptSourceFactory;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rusakovich
 */
public class SensorScriptDecoratorTest {

    private ScriptSource scriptSource;

    public SensorScriptDecoratorTest() {
    }

    @Before
    public void setUp() throws ScriptException {
        this.scriptSource = ScriptSourceFactory.createScriptResource("file:src/test/resources/sensor1Decorator.js");
    }

    /**
     * Test of getValue method, of class ScriptableSensorDecorator.
     */
    @Test
    public void testGetValue() throws Exception {
        System.out.println("***** getValue *****");
        PlainSensor sensor1 = new PlainSensor();
        sensor1.setValue(4.0);
        sensor1.setLabel("label");

        SensorScriptDecorator instance = new SensorScriptDecorator(sensor1, "sensor1Decorator");
        instance.bind(scriptSource);
                
        double expResult = 400.0;
        double result = instance.getValue();
        assertEquals(expResult, result, 0.05);
    }

    @Test
    public void testGetLabel() throws Exception {
        System.out.println("***** getLabel *****");
        PlainSensor sensor1 = new PlainSensor();
        sensor1.setLabel("label");

        SensorScriptDecorator instance = new SensorScriptDecorator(sensor1, "sensor1Decorator");
        instance.bind(scriptSource);

        String expResult = "decorated label";
        String result = instance.getLabel();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetAddress() throws Exception {
        System.out.println("***** getAddress *****");
        PlainSensor sensor1 = new PlainSensor();
        sensor1.setAddress("address");

        SensorScriptDecorator instance = new SensorScriptDecorator(sensor1, "sensor1Decorator");
        instance.bind(scriptSource);

        String expResult = "address";
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }

}
