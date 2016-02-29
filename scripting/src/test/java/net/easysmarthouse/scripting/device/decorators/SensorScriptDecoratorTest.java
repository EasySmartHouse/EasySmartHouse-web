/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.device.decorators;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import net.easysmarthouse.provider.device.sensor.PlainSensor;
import net.easysmarthouse.scripting.util.FileHelper;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rusakovich
 */
public class SensorScriptDecoratorTest {

    private ScriptEngine engine;
    private String script;

    public SensorScriptDecoratorTest() {
    }

    @Before
    public void setUp() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        this.engine = manager.getEngineByName("JavaScript");
        this.script = FileHelper.readFile("src/test/resources/sensor1Decorator.js");
    }

    /**
     * Test of getValue method, of class ScriptableSensorDecorator.
     */
    @Test
    public void testGetValue() throws Exception {
        System.out.println("***** getValue *****");
        PlainSensor sensor1 = new PlainSensor();
        sensor1.setValue(4.0);

        SensorScriptDecorator instance = new SensorScriptDecorator(sensor1, "sensor1Decorator");
        instance.setScript(script);
        instance.setScriptEngine(engine);

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
        instance.setScript(script);
        instance.setScriptEngine(engine);

        String expResult = "decorated label";
        String result = instance.getLabel();
        assertEquals(expResult, result);
    }

}
