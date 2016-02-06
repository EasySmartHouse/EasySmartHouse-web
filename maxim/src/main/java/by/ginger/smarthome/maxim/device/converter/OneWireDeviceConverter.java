/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.maxim.device.converter;

import by.ginger.smarthome.maxim.device.actuator.SwitchContainerAdapter;
import by.ginger.smarthome.maxim.device.sensors.HumidityContainerAdapter;
import by.ginger.smarthome.maxim.device.sensors.PressureContainerAdapter;
import by.ginger.smarthome.maxim.device.sensors.TemperatureContainerAdapter;
import by.ginger.smarthome.provider.device.Device;
import by.ginger.smarthome.provider.device.converter.AbstractDeviceConverter;
import com.dalsemi.onewire.container.HumidityContainer;
import com.dalsemi.onewire.container.OneWireContainer;
import com.dalsemi.onewire.container.OneWireContainer20;
import com.dalsemi.onewire.container.SwitchContainer;
import com.dalsemi.onewire.container.TemperatureContainer;

/**
 *
 * @author rusakovich
 */
public class OneWireDeviceConverter extends AbstractDeviceConverter<OneWireContainer> {
//TODO do smart!

    @Override
    public Device getDevice(OneWireContainer container) {
        if (container instanceof SwitchContainer) {
            return new SwitchContainerAdapter((SwitchContainer) container);
        }
        if (container instanceof HumidityContainer) {
            return new HumidityContainerAdapter((HumidityContainer) container);
        }
        if (container instanceof OneWireContainer20) {
            return new PressureContainerAdapter((OneWireContainer20) container);
        }
        if (container instanceof TemperatureContainer) {
            return new TemperatureContainerAdapter((TemperatureContainer) container);
        }
        return null;
    }

}
