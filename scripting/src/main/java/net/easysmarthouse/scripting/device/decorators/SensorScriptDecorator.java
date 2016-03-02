/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.device.decorators;

import net.easysmarthouse.scripting.device.DeviceScriptBinding;
import javax.script.Invocable;
import javax.script.ScriptException;
import net.easysmarthouse.provider.device.DeviceType;
import net.easysmarthouse.provider.device.exception.DeviceException;
import net.easysmarthouse.provider.device.sensor.Sensor;
import net.easysmarthouse.provider.device.sensor.SensorType;
import net.easysmarthouse.scripting.ScriptSource;

/**
 *
 * @author rusakovich
 */
public class SensorScriptDecorator extends DeviceScriptBinding<Sensor> implements Sensor {

    private final String name;
    private Invocable inv;
    private Object obj;

    public SensorScriptDecorator(Sensor sensor, String name) {
        super(sensor);
        this.name = name;
    }

    @Override
    public synchronized double getValue() throws DeviceException {
        try {
            return (Double) inv.invokeMethod(obj, "getValue");
        } catch (ScriptException ex) {
            throw new DeviceException(ex);
        } catch (NoSuchMethodException ex) {
            return device.getValue();
        }
    }

    @Override
    public SensorType getSensorType() {
        return device.getSensorType();
    }

    @Override
    public synchronized String getAddress() {
        try {
            return (String) inv.invokeMethod(obj, "getAddress");
        } catch (ScriptException ex) {
            throw new IllegalStateException(ex);
        } catch (NoSuchMethodException ex) {
            return device.getAddress();
        }
    }

    @Override
    public synchronized String getLabel() {
        try {
            return (String) inv.invokeMethod(obj, "getLabel");
        } catch (ScriptException ex) {
            throw new IllegalStateException(ex);
        } catch (NoSuchMethodException ex) {
            return device.getLabel();
        }
    }

    @Override
    public synchronized String getDescription() {
        try {
            return (String) inv.invokeMethod(obj, "getDescription");
        } catch (ScriptException ex) {
            throw new IllegalStateException(ex);
        } catch (NoSuchMethodException ex) {
            return device.getDescription();
        }
    }

    @Override
    public DeviceType getDeviceType() {
        return device.getDeviceType();
    }

    @Override
    public void bind(ScriptSource scriptSource) {
        super.bind(scriptSource);
        this.inv = (Invocable) scriptSource.getScriptEngine();
        this.obj = bindingScope.get(name);
    }

    @Override
    public void unbind() {
        super.unbind();
        if (bindingScope != null) {
            bindingScope.remove(name);
        }
    }

}
