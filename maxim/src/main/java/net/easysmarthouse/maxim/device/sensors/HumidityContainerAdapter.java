/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.maxim.device.sensors;

import net.easysmarthouse.maxim.device.OneWireContainerAdapter;
import net.easysmarthouse.provider.device.DeviceType;
import net.easysmarthouse.provider.device.exception.DeviceException;
import net.easysmarthouse.provider.device.sensor.Sensor;
import net.easysmarthouse.provider.device.sensor.SensorType;
import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.container.HumidityContainer;
import com.dalsemi.onewire.container.OneWireContainer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author rusakovich
 */
public class HumidityContainerAdapter extends OneWireContainerAdapter implements Sensor {

    private static final Log log = LogFactory.getLog(HumidityContainerAdapter.class);

    private final HumidityContainer humidityContainer;

    public HumidityContainerAdapter(HumidityContainer humidityContainer) {
        super((OneWireContainer) humidityContainer);
        this.humidityContainer = humidityContainer;
    }

    @Override
    public double getValue() throws DeviceException {
        try {
            // Read device state.
            byte[] state = humidityContainer.readDevice();
            // Perform humidity conversion, supply power for conversion.
            humidityContainer.doHumidityConvert(state);
            // Read device state
            state = humidityContainer.readDevice();
            // Calculate humidity value based on device state.
            return humidityContainer.getHumidity(state);
        } catch (OneWireException ex) {
            log.error("Humidity reading error", ex);
            throw new DeviceException(ex);
        }
    }

    @Override
    public SensorType getSensorType() {
        return SensorType.HumiditySensor;
    }

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Sensor;
    }

}
