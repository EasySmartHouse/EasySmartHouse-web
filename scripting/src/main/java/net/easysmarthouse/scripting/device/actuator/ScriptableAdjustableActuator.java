/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.device.actuator;

import java.io.Closeable;
import java.io.IOException;
import net.easysmarthouse.provider.device.DeviceType;
import net.easysmarthouse.provider.device.actuator.ActuatorType;
import net.easysmarthouse.provider.device.actuator.AdjustableActuator;
import net.easysmarthouse.provider.device.exception.DeviceException;
import net.easysmarthouse.scripting.ScriptSource;
import net.easysmarthouse.scripting.device.ScriptableDevice;
import net.easysmarthouse.scripting.device.ScriptableDevicePrototype;

/**
 *
 * @author rusakovich
 */
public class ScriptableAdjustableActuator implements AdjustableActuator, ScriptableDevice, Closeable {

    private final ScriptableDevicePrototype prototype;

    public ScriptableAdjustableActuator(ScriptableDevicePrototype prototype) {
        this.prototype = prototype;
    }

    @Override
    public Double getState() throws DeviceException {
        return (Double) prototype.invoke("getState");
    }

    @Override
    public void setState(Double state) throws DeviceException {
        prototype.invoke("setState", state);
    }

    @Override
    public ActuatorType getActuatorType() {
        return ActuatorType.valueOf((String) prototype.getField("actuatorType"));
    }

    @Override
    public String getAddress() {
        return (String) prototype.getField("address");
    }

    @Override
    public String getLabel() {
        return (String) prototype.getField("label");
    }

    @Override
    public String getDescription() {
        return (String) prototype.getField("description");
    }

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Actuator;
    }

    @Override
    public void close() throws IOException {
        prototype.unbind();
    }

    @Override
    public void bind(ScriptSource scriptSource) {
        prototype.bind(scriptSource);
    }

    @Override
    public void unbind() {
        prototype.unbind();
    }

    @Override
    public Double getMinValue() {
        return (Double) prototype.getField("minValue");
    }

    @Override
    public Double getMaxValue() {
        return (Double) prototype.getField("maxValue");
    }

    @Override
    public Double getDefaultValue() {
        return (Double) prototype.getField("defaultValue");
    }

    @Override
    public Double getChangeStep() {
        return (Double) prototype.getField("changeStep");
    }
}
