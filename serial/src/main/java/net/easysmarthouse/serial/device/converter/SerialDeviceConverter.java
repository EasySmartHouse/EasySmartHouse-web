/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.serial.device.converter;

import java.util.LinkedList;
import java.util.List;
import jssc.SerialPort;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.IdleDevice;
import net.easysmarthouse.provider.device.converter.DeviceConverter;
import net.easysmarthouse.provider.device.discovery.DeviceDiscovery;
import net.easysmarthouse.serial.device.actuator.Relay2ChannelsDeviceDiscovery;
import net.easysmarthouse.serial.device.actuator.Relay4ChannelsDeviceDiscovery;
import net.easysmarthouse.serial.device.actuator.RelaySerialDevice;

/**
 *
 * @author rusakovich
 */
public class SerialDeviceConverter implements DeviceConverter<SerialPort> {

    private DeviceDiscovery<SerialPort> relay2ChannelsDeviceDiscovery = new Relay2ChannelsDeviceDiscovery();
    private DeviceDiscovery<SerialPort> relay4ChannelsDeviceDiscovery = new Relay4ChannelsDeviceDiscovery();

    @Override
    public Device getDevice(SerialPort port) {
        return new IdleDevice();
    }

    @Override
    public List<Device> getDevices(List<SerialPort> ports) {
        LinkedList<Device> devices = new LinkedList<>();
        for (SerialPort serialPort : ports) {

            if (relay2ChannelsDeviceDiscovery.isDeviceAvailable(serialPort)) {
                devices.add(new RelaySerialDevice(serialPort, (byte) 0));
                devices.add(new RelaySerialDevice(serialPort, (byte) 1));
            }

        }

        return devices;
    }

}
