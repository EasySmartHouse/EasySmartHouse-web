/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.device.converter;

import net.easysmarthouse.provider.device.Device;
import static net.easysmarthouse.provider.device.DeviceType.Sensor;
import net.easysmarthouse.provider.device.converter.AbstractDeviceConverter;
import net.easysmarthouse.scripting.device.ScriptableDevicePrototype;
import net.easysmarthouse.scripting.device.sensor.ScriptableSensor;

/**
 *
 * @author rusakovich
 */
public class ScriptableDeviceConverter extends AbstractDeviceConverter<ScriptableDevicePrototype> {

    @Override
    public Device getDevice(ScriptableDevicePrototype handler) {
        if (handler.getDeviceType() == Sensor) {
            return new ScriptableSensor(handler);

        }
        return null;
    }

}
