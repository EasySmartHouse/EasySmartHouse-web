/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.hid.device.sensors;

import net.easysmarthouse.hid.device.AbstractHidDevice;
import net.easysmarthouse.provider.device.Closeable;
import net.easysmarthouse.hid.device.HidDescriptor;
import net.easysmarthouse.hid.device.TemperDescriptor;
import net.easysmarthouse.hid.device.natives.NativeDeviceException;
import net.easysmarthouse.hid.device.natives.Temper;
import net.easysmarthouse.provider.device.DeviceType;
import net.easysmarthouse.provider.device.exception.DeviceException;
import net.easysmarthouse.provider.device.sensor.Sensor;
import net.easysmarthouse.provider.device.sensor.SensorType;
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
