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
import com.dalsemi.onewire.container.OneWireContainer20;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author rusakovich
 */
public class PressureContainerAdapter extends OneWireContainerAdapter implements Sensor {

    private static final Log log = LogFactory.getLog(PressureContainerAdapter.class);

    private final OneWireContainer20 container;

    private static final int CHANNEL_DEFAULT = 1;
    private static final double COEFF_DEFAULT = 0;
    private static final double DIFF_DEFAULT = 0;

    private int channel;
    private double coeff;
    private double diff;

    public PressureContainerAdapter(OneWireContainer20 container) {
        super(container);
        this.container = container;

        channel = CHANNEL_DEFAULT;
        coeff = COEFF_DEFAULT;
        diff = DIFF_DEFAULT;
    }

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Sensor;
    }

    /**
     * Read value from AD converter and convert this value to pressure. For
     * conversion use rule:
     *
     * <pre> pressure = ADresult * coeff + diff</pre>
     */
    @Override
    public double getValue() throws DeviceException {
        try {
            byte[] state = container.readDevice();
            // Check if specific channel is enabled
            boolean channelEnabled = container.getOutputState(channel, state);
            // Enable channel if needed
            if (channelEnabled) {
                container.setOutput(channel, true, true, state);
                container.writeDevice(state);
                state = container.readDevice();
            }
            container.doADConvert(channel, state);
            // Calculate pressure value based on configured coefficient
            double result = container.getADVoltage(channel, state);
            result = result * coeff + diff;
            return result;
        } catch (OneWireException ex) {
            log.error("Pressure reading exception", ex);
            throw new DeviceException(ex);
        }
    }

    @Override
    public SensorType getSensorType() {
        return SensorType.PressureSensor;
    }

}
