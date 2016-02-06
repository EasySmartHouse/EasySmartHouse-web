/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.maxim.device.sensors;

import by.ginger.smarthome.maxim.device.OneWireContainerAdapter;
import by.ginger.smarthome.provider.device.DeviceType;
import by.ginger.smarthome.provider.device.exception.DeviceException;
import by.ginger.smarthome.provider.device.sensor.Sensor;
import by.ginger.smarthome.provider.device.sensor.SensorType;
import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.container.OneWireContainer;
import com.dalsemi.onewire.container.TemperatureContainer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author rusakovich
 */
public class TemperatureContainerAdapter extends OneWireContainerAdapter implements Sensor {

    private static final Log log = LogFactory.getLog(TemperatureContainerAdapter.class);

    private final TemperatureContainer temperatureContainer;
    private boolean forceConvertion = true;

    public TemperatureContainerAdapter(TemperatureContainer temperatureContainer) {
        super((OneWireContainer) temperatureContainer);
        this.temperatureContainer = temperatureContainer;
    }

    @Override
    public double getValue() throws DeviceException {
        try {
            byte[] state = temperatureContainer.readDevice();
            // If conversion enabled initiate it, otherwise skip it and just read value from device.
            // This option is useful if conversion has been performed yearly one time for all devices.
            if (forceConvertion) {
                temperatureContainer.doTemperatureConvert(state);
                state = temperatureContainer.readDevice();
            }
            return temperatureContainer.getTemperature(state);
        } catch (OneWireException ex) {
            log.error("Temperature reading error", ex);
            throw new DeviceException(ex);
        }
    }

    @Override
    public SensorType getSensorType() {
        return SensorType.TemperatureSensor;
    }

    public boolean isForceConvertion() {
        return forceConvertion;
    }

    public void setForceConvertion(boolean forceConvertion) {
        this.forceConvertion = forceConvertion;
    }

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Sensor;
    }

}
