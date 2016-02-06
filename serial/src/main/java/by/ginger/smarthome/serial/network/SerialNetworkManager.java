/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.serial.network;

import by.ginger.smarthome.network.AbstractStorableNetworkManager;
import by.ginger.smarthome.network.exception.NetworkException;
import by.ginger.smarthome.network.extension.ConversionExtension;
import by.ginger.smarthome.network.extension.IdleConversionExtension;
import by.ginger.smarthome.network.util.AddressHelper;
import by.ginger.smarthome.provider.device.Closeable;
import by.ginger.smarthome.provider.device.Device;
import by.ginger.smarthome.serial.device.SerialDeviceNotAvailableException;
import by.ginger.smarthome.serial.device.actuator.RelaySerialDevice;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jssc.SerialPortList;

/**
 *
 * @author mirash
 */
public class SerialNetworkManager extends AbstractStorableNetworkManager {

    private final static Pattern COM_PORT_PATTERN = Pattern.compile("^COM\\d{1,2}$");
    private final ConversionExtension conversionExtension = new IdleConversionExtension();

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

            Matcher portMatcher = COM_PORT_PATTERN.matcher(portName);
            if (portMatcher.matches()) {
                int portNum = Integer.valueOf(portName.replaceFirst("COM", ""));

                RelaySerialDevice relayCh1 = null;
                RelaySerialDevice relayCh2 = null;
                try {
                    relayCh1 = new RelaySerialDevice((byte) portNum, (byte) 0);
                    relayCh2 = new RelaySerialDevice((byte) portNum, (byte) 1);

                    relayCh1.checkAvailable();
                    relayCh2.checkAvailable();

                    devices.add(relayCh1);
                    devices.add(relayCh2);
                } catch (SerialDeviceNotAvailableException availableException) {
                    if (relayCh1 != null) {
                        relayCh1.closeOpened();
                    }

                    if (relayCh2 != null) {
                        relayCh2.closeOpened();
                    }
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
