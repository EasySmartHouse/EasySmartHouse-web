/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.device.converter;

import net.easysmarthouse.provider.device.Device;
import static net.easysmarthouse.provider.device.DeviceType.Actuator;
import static net.easysmarthouse.provider.device.DeviceType.Sensor;
import net.easysmarthouse.provider.device.actuator.ActuatorType;
import static net.easysmarthouse.provider.device.actuator.ActuatorType.adjustableActuator;
import static net.easysmarthouse.provider.device.actuator.ActuatorType.switchActuator;
import net.easysmarthouse.provider.device.converter.AbstractDeviceConverter;
import net.easysmarthouse.scripting.device.ScriptableDevicePrototype;
import net.easysmarthouse.scripting.device.actuator.ScriptableAdjustableActuator;
import net.easysmarthouse.scripting.device.actuator.ScriptableSwitchActuator;
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

        if (handler.getDeviceType() == Actuator) {
            String actuatorTypeStr = (String) handler.getField("actuatorType");
            ActuatorType actuatorType = ActuatorType.valueOf(ActuatorType.class, actuatorTypeStr);
            if (actuatorType == switchActuator) {
                return new ScriptableSwitchActuator(handler);
            }

            if (actuatorType == adjustableActuator) {
                return new ScriptableAdjustableActuator(handler);
            }
        }

        return null;
    }

}
