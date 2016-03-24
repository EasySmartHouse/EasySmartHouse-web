/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.serial.device.actuator;

import java.util.Arrays;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;
import net.easysmarthouse.provider.device.discovery.DeviceDiscovery;
import net.easysmarthouse.serial.util.SerialPortHelper;

/**
 *
 * @author rusakovich
 */
public class Relay2ChannelsDeviceDiscovery implements DeviceDiscovery<SerialPort> {

    private static final Logger log = Logger.getLogger(Relay2ChannelsDeviceDiscovery.class.getName());

    private static final int MAX_CHANNELS_COUNT = 2;

    private static final int READ_TIMEOUT = 300;

    @Override
    public boolean isDeviceAvailable(SerialPort port) {
        try {
            SerialPortHelper.initPort(port, SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

            for (int channel = 0; channel < getChannelsCount(); channel++) {

                byte[] checkCmd = CommandBuilder.INSTANCE_4CH.getControlCommand(ControlCommand.READING_STATUS, channel);

                try {
                    port.writeBytes(checkCmd);
                    byte[] response = port.readBytes(8, READ_TIMEOUT);

                    if (!Arrays.equals(CommandBuilder.READING_STATE_RESPONSE, response)) {
                        return false;
                    }
                } catch (SerialPortException ex) {
                    log.severe(ex.getMessage());
                    return false;
                } catch (SerialPortTimeoutException ex) {
                    log.severe(ex.getMessage());
                    return false;
                }

            }
        } finally {
            SerialPortHelper.closePort(port);
        }
        return true;
    }

    public int getChannelsCount() {
        return MAX_CHANNELS_COUNT;
    }

}
