/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.device.sensor;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import net.easysmarthouse.provider.device.DeviceType;
import net.easysmarthouse.provider.device.exception.DeviceException;
import net.easysmarthouse.provider.device.sensor.Sensor;
import net.easysmarthouse.provider.device.sensor.SensorType;
import net.easysmarthouse.scripting.device.AbstractScriptableDevice;

/**
 *
 * @author rusakovich
 */
public class ScriptableSensor extends AbstractScriptableDevice implements Sensor {

    private Invocable inv;
    private Object obj;
    private final String name;

    public ScriptableSensor(String name) {
        this.name = name;
    }

    private Object getScriptObjectField(String fieldName) throws ScriptException {
        StringBuilder builder = new StringBuilder(name).append(".").append(fieldName);
        return scriptEngine.eval(builder.toString());
    }

    @Override
    public double getValue() throws DeviceException {
        try {
            return (Double) inv.invokeMethod(obj, "getValue");
        } catch (ScriptException ex) {
            throw new DeviceException(ex);
        } catch (NoSuchMethodException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public SensorType getSensorType() {
        try {
            return SensorType.valueOf((String) getScriptObjectField("sensorType"));
        } catch (ScriptException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public String getAddress() {
        try {
            return (String) getScriptObjectField("address");
        } catch (ScriptException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public String getLabel() {
        try {
            return (String) getScriptObjectField("label");
        } catch (ScriptException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public String getDescription() {
        try {
            return (String) getScriptObjectField("description");
        } catch (ScriptException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public DeviceType getDeviceType() {
        try {
            return DeviceType.valueOf((String) getScriptObjectField("deviceType"));
        } catch (ScriptException ex) {
            return DeviceType.Unknown;
        }
    }

    @Override
    public void setScriptEngine(ScriptEngine scriptEngine) {
        super.setScriptEngine(scriptEngine);
        this.inv = (Invocable) scriptEngine;
        this.obj = scriptEngine.get(name);
    }

}
