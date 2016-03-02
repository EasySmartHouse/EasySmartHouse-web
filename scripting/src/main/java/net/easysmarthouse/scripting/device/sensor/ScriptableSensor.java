/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.device.sensor;

import javax.script.Invocable;
import javax.script.ScriptException;
import net.easysmarthouse.provider.device.DeviceType;
import net.easysmarthouse.provider.device.exception.DeviceException;
import net.easysmarthouse.provider.device.sensor.Sensor;
import net.easysmarthouse.provider.device.sensor.SensorType;
import net.easysmarthouse.scripting.ScriptSource;
import net.easysmarthouse.scripting.device.ScriptableDevice;

/**
 *
 * @author rusakovich
 */
public class ScriptableSensor implements ScriptableDevice, Sensor {

    private Invocable inv;
    private Object obj;
    private final String name;
    private ScriptSource scriptSource;

    public ScriptableSensor(String name) {
        this.name = name;
    }

    private synchronized Object getScriptObjectField(String fieldName) throws ScriptException {
        StringBuilder builder = new StringBuilder(name).append(".").append(fieldName);
        return scriptSource.getScriptEngine().eval(builder.toString());
    }

    @Override
    public synchronized double getValue() throws DeviceException {
        try {
            return (Double) inv.invokeMethod(obj, "getValue");
        } catch (ScriptException ex) {
            throw new DeviceException(ex);
        } catch (NoSuchMethodException ex) {
            throw new IllegalStateException("getValue method is required", ex);
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
            throw new IllegalStateException("'address' field is required", ex);
        }
    }

    @Override
    public String getLabel() {
        try {
            return (String) getScriptObjectField("label");
        } catch (ScriptException ex) {
            throw new IllegalStateException("'label' field is required", ex);
        }
    }

    @Override
    public String getDescription() {
        try {
            return (String) getScriptObjectField("description");
        } catch (ScriptException ex) {
            return null;
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
    public void bind(ScriptSource scriptSource) {
        this.scriptSource = scriptSource;
        try {
            scriptSource.getScriptEngine().eval(scriptSource.getScript());
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        this.inv = (Invocable) scriptSource.getScriptEngine();
        this.obj = scriptSource.getScriptEngine().get(name);
    }

    @Override
    public void unbind() {
        if (scriptSource != null) {
            scriptSource.getScriptEngine().put(name, null);
        }
    }

}
