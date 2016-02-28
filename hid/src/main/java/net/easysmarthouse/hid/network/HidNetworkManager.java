/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.hid.network;

import net.easysmarthouse.hid.device.AbstractHidDevice;
import net.easysmarthouse.provider.device.Closeable;
import net.easysmarthouse.hid.device.HidAddressHelper;
import net.easysmarthouse.network.AbstractStorableNetworkManager;
import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.network.extension.ConversionExtension;
import net.easysmarthouse.network.extension.IdleConversionExtension;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.converter.DeviceConverter;
import java.util.ArrayList;
import java.util.List;
import org.usb4java.Context;
import org.usb4java.DeviceDescriptor;
import org.usb4java.DeviceList;
import org.usb4java.LibUsb;
import org.usb4java.LibUsbException;

/**
 *
 * @author rusakovich
 */
public class HidNetworkManager extends AbstractStorableNetworkManager {

    private DeviceConverter<DeviceDescriptor> deviceConverter;
    private final Context context;

    public HidNetworkManager() {
        this.context = new Context();
    }

    @Override
    public void init() {
        if (deviceConverter == null) {
            throw new IllegalStateException("Please, setup appreciate device converter");
        }

        int result = LibUsb.init(context);
        if (result != LibUsb.SUCCESS) {
            throw new LibUsbException("Unable to initialize libusb.", result);
        }
    }

    @Override
    public void destroy() {
        closeAll();
        LibUsb.exit(context);
    }

    @Override
    public void startSession() throws NetworkException {
    }

    @Override
    public void endSession() throws NetworkException {
    }

    private void closeAll() {
        for (Device device : devices) {
            if (device instanceof Closeable) {
                Closeable closeable = (Closeable) device;
                closeable.close();
            }
        }
    }

    @Override
    public void refreshDevices() throws NetworkException {
        if (!devices.isEmpty()) {
            return;
        }

        DeviceList list = new DeviceList();
        int result = LibUsb.getDeviceList(context, list);
        if (result < 0) {
            throw new NetworkException("Unable to get device list");
        }

        try {
            for (org.usb4java.Device device : list) {
                DeviceDescriptor descriptor = new DeviceDescriptor();
                result = LibUsb.getDeviceDescriptor(device, descriptor);
                if (result != LibUsb.SUCCESS) {
                    throw new NetworkException("Unable to read device descriptor");
                }

                Device networkDevice = deviceConverter.getDevice(descriptor);
                if (networkDevice != null) {
                    devices.add(networkDevice);
                }
            }
        } finally {
            LibUsb.freeDeviceList(list, true);
        }
    }

    @Override
    public List<Device> getDevices() throws NetworkException {
        refreshDevices();
        return devices;
    }

    @Override
    public List<Long> getDevicesAddresses() throws NetworkException {
        List<Long> addresses = new ArrayList<>();
        for (Device device : devices) {
            AbstractHidDevice hidDevice = (AbstractHidDevice) device;
            addresses.add(HidAddressHelper.getLong(hidDevice));
        }

        return addresses;
    }

    @Override
    public boolean isDevicePresent(String address) throws NetworkException {
        refreshDevices();

        for (Device device : devices) {
            if (device.getAddress().equalsIgnoreCase(address)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public ConversionExtension getConversionExtension() {
        return new IdleConversionExtension();
    }

    public void setDeviceConverter(DeviceConverter deviceConverter) {
        this.deviceConverter = deviceConverter;
    }
}
