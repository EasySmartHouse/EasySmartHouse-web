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
import net.easysmarthouse.provider.device.actuator.SwitchActuator;
import net.easysmarthouse.provider.device.exception.DeviceException;
import net.easysmarthouse.scripting.ScriptSource;
import net.easysmarthouse.scripting.device.ScriptableDevice;
import net.easysmarthouse.scripting.device.ScriptableDevicePrototype;

/**
 *
 * @author rusakovich
 */
public class ScriptableSwitchActuator implements SwitchActuator, ScriptableDevice, Closeable {

    private final ScriptableDevicePrototype prototype;

    public ScriptableSwitchActuator(ScriptableDevicePrototype prototype) {
        this.prototype = prototype;
    }

    @Override
    public Boolean getState() throws DeviceException {
        return (Boolean) prototype.invoke("getState");
    }

    @Override
    public void setState(Boolean state) throws DeviceException {
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
        prototype.destroy();
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

}
