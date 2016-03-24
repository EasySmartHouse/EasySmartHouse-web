/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.serial.network;

import net.easysmarthouse.network.AbstractStorableNetworkManager;
import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.network.extension.ConversionExtension;
import net.easysmarthouse.network.extension.IdleConversionExtension;
import net.easysmarthouse.network.util.AddressHelper;
import net.easysmarthouse.provider.device.Closeable;
import net.easysmarthouse.provider.device.Device;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jssc.SerialPort;
import jssc.SerialPortList;
import net.easysmarthouse.provider.device.converter.DeviceConverter;
import net.easysmarthouse.serial.device.converter.SerialDeviceConverter;

/**
 *
 * @author mirash
 */
public class SerialNetworkManager extends AbstractStorableNetworkManager {

    private final ConversionExtension conversionExtension = new IdleConversionExtension();
    private DeviceConverter<SerialPort> serialDeviceConverter = new SerialDeviceConverter();

    public SerialNetworkManager() {
    }

    @Override
    public void init() {
    }

    @Override
    public void destroy() {
        for (Device device : devices) {
            if (device instanceof Closeable) {
                ((Closeable) device).close();
            }
        }
        devices.clear();
    }

    @Override
    public void startSession() throws NetworkException {
        if (!devices.isEmpty()) {
            return;
        }

        String[] portNames = SerialPortList.getPortNames();
        for (String portName : portNames) {
            List<Device> serialDevices = serialDeviceConverter.getDevices(Collections.singletonList(new SerialPort(portName)));
            devices.addAll(serialDevices);
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
        List<Long> addresses = new ArrayList<>();
        for (Device device : devices) {
            long addr = AddressHelper.buidldHash(device.getAddress(), 127l);
            addresses.add(addr);
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
        return this.conversionExtension;
    }
}
