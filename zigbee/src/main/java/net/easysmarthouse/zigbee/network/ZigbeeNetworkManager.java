/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.zigbee.network;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import jssc.SerialPort;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import net.easysmarthouse.network.AbstractStorableNetworkManager;
import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.network.extension.ConversionExtension;
import net.easysmarthouse.network.extension.IdleConversionExtension;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.converter.DeviceConverter;
import net.easysmarthouse.serial.util.SerialPortHelper;
import net.easysmarthouse.zigbee.device.converter.ZigbeeDeviceConverter;

/**
 *
 * @author rusakovich
 */
public class ZigbeeNetworkManager extends AbstractStorableNetworkManager {

    private final ConversionExtension conversionExtension = new IdleConversionExtension();
    private final DeviceConverter deviceConverter = new ZigbeeDeviceConverter();
    private final AtomicBoolean started = new AtomicBoolean(false);

    private String port;
    private SerialPort serialPort;

    @Override
    public void init() {
    }

    @Override
    public void destroy() {
        if (started.compareAndSet(true, false)) {
            SerialPortHelper.closePort(serialPort);
        }
    }

    private SerialPortEventListener createCoordinatorListener() {
        ZigbeeCoordinatorListener listener = new ZigbeeCoordinatorListener(this, serialPort);
        listener.setDeviceConverter(deviceConverter);
        return listener;
    }

    @Override
    public void startSession() throws NetworkException {
        if (port == null || SerialPortHelper.getPortNumber(port) == -1) {
            throw new IllegalStateException("Invalid port specified: [" + port + "]");
        }

        if (started.compareAndSet(false, true)) {
            this.serialPort = new SerialPort(port);
            SerialPortHelper.initPort(serialPort,
                    SerialPort.BAUDRATE_38400,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            if (serialPort.isOpened()) {
                try {
                    serialPort.addEventListener(createCoordinatorListener(), SerialPort.MASK_RXCHAR);
                } catch (SerialPortException ex) {
                    throw new NetworkException(ex);
                }
            }
        }
    }

    @Override
    public void endSession() throws NetworkException {
    }

    @Override
    public void refreshDevices() throws NetworkException {
    }

    @Override
    public List<Device> getDevices() throws NetworkException {
        return devices;
    }

    @Override
    public List<Long> getDevicesAddresses() throws NetworkException {
        List<Long> addresses = new ArrayList<>(devices.size());
        for (Device zigbeeDevice : devices) {
            addresses.add(Long.parseLong(zigbeeDevice.getAddress(), 16));
        }
        return addresses;
    }

    @Override
    public boolean isDevicePresent(String address) throws NetworkException {
        for (Device device : devices) {
            if (device.getAddress().equalsIgnoreCase(address)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ConversionExtension getConversionExtension() {
        return conversionExtension;
    }

    public void setPort(String port) {
        this.port = port;
    }

}
