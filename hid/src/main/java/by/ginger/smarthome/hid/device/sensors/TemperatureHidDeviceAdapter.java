/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.hid.device.sensors;

import by.ginger.smarthome.hid.device.AbstractHidDevice;
import by.ginger.smarthome.provider.device.Closeable;
import by.ginger.smarthome.hid.device.HidDescriptor;
import by.ginger.smarthome.hid.device.TemperDescriptor;
import by.ginger.smarthome.hid.device.natives.NativeDeviceException;
import by.ginger.smarthome.hid.device.natives.Temper;
import by.ginger.smarthome.provider.device.DeviceType;
import by.ginger.smarthome.provider.device.exception.DeviceException;
import by.ginger.smarthome.provider.device.sensor.Sensor;
import by.ginger.smarthome.provider.device.sensor.SensorType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author rusakovich
 *
 * Device adapter for TemperV1.4
 */
public class TemperatureHidDeviceAdapter extends AbstractHidDevice implements Sensor, Closeable {

    private static final Log log = LogFactory.getLog(TemperatureHidDeviceAdapter.class);
    private Temper temper;
    private final short serial;

    public TemperatureHidDeviceAdapter(byte port, short serial) {
        super(port);
        this.serial = serial;
        try {
            temper = new Temper(serial);
        } catch (NativeDeviceException ex) {
            log.error("Cannot init Temper device", ex);
        }
    }

    @Override
    public double getValue() throws DeviceException {
        if (temper == null) {
            throw new DeviceException("Cannot read Temper device: " + getAddress());
        }
        try {
            return temper.readTemperature();
        } catch (NativeDeviceException ex) {
            throw new DeviceException(ex.getMessage());
        }
    }

    @Override
    public SensorType getSensorType() {
        return SensorType.TemperatureSensor;
    }

    @Override
    public String getLabel() {
        return "PCsensor Temper device";
    }

    @Override
    public String getDescription() {
        return getLabel() + " " + getAddress();
    }

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Sensor;
    }

    @Override
    public int getSerialNumber() {
        return serial;
    }

    @Override
    public HidDescriptor getDescriptor() {
        return TemperDescriptor.getInstance();
    }

    public String toString() {
        return getDescription();
    }

    @Override
    public void close() {
        try {
            if (temper != null) {
                temper.close();
            }
        } catch (NativeDeviceException ex) {
        }
    }
}
