/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.device.decorators;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import net.easysmarthouse.provider.device.DeviceType;
import net.easysmarthouse.provider.device.exception.DeviceException;
import net.easysmarthouse.provider.device.sensor.Sensor;
import net.easysmarthouse.provider.device.sensor.SensorType;

/**
 *
 * @author rusakovich
 */
public class SensorScriptDecorator extends DeviceScriptDecorator<Sensor> implements Sensor {

    private final String name;
    private Invocable inv;
    private Object obj;

    public SensorScriptDecorator(Sensor sensor, String name) {
        super(sensor);
        this.name = name;
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
        return device.getSensorType();
    }

    @Override
    public String getAddress() {
        try {
            return (String) inv.invokeMethod(obj, "getValue");
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public String getLabel() {
        try {
            return (String) inv.invokeMethod(obj, "getLabel");
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public String getDescription() {
        try {
            return (String) inv.invokeMethod(obj, "getDescription");
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public DeviceType getDeviceType() {
        return device.getDeviceType();
    }

    @Override
    public void setScriptEngine(ScriptEngine scriptEngine) {
        super.setScriptEngine(scriptEngine);
        this.inv = (Invocable) this.scriptEngine;
        this.obj = bindingScope.get(name);
    }

}
