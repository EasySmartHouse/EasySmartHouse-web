/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.device.sensor;

import java.io.Closeable;
import java.io.IOException;
import net.easysmarthouse.provider.device.DeviceType;
import static net.easysmarthouse.provider.device.DeviceType.Sensor;
import net.easysmarthouse.provider.device.exception.DeviceException;
import net.easysmarthouse.provider.device.sensor.Sensor;
import net.easysmarthouse.provider.device.sensor.SensorType;
import net.easysmarthouse.scripting.ScriptSource;
import net.easysmarthouse.scripting.device.ScriptableDevice;
import net.easysmarthouse.scripting.device.ScriptableDevicePrototype;

/**
 *
 * @author rusakovich
 */
public class ScriptableSensor implements Sensor, ScriptableDevice, Closeable {

    private final ScriptableDevicePrototype prototype;

    public ScriptableSensor(ScriptableDevicePrototype prototype) {
        this.prototype = prototype;
    }

    @Override
    public synchronized double getValue() throws DeviceException {
        return (Double) prototype.invoke("getValue");
    }

    @Override
    public SensorType getSensorType() {
        return SensorType.valueOf((String) prototype.getField("sensorType"));

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
        return Sensor;
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

}
