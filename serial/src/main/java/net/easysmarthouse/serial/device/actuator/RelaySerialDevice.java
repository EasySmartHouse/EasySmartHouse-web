/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.serial.device.actuator;

import net.easysmarthouse.provider.device.Closeable;
import net.easysmarthouse.provider.device.DeviceType;
import net.easysmarthouse.provider.device.actuator.ActuatorType;
import net.easysmarthouse.provider.device.actuator.SwitchActuator;
import net.easysmarthouse.provider.device.exception.DeviceException;
import net.easysmarthouse.serial.device.AbstractSerialDevice;
import net.easysmarthouse.serial.device.SerialDeviceNotAvailableException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;

/**
 *
 * @author mirash
 */
public class RelaySerialDevice extends AbstractSerialDevice implements SwitchActuator, Closeable {

    private static final int READ_TIMEOUT = 300;
    private static Map<Integer, SerialPort> relayPorts = Collections.synchronizedMap(
            new HashMap<Integer, SerialPort>());
    private final byte channel;
    private final SerialPort serialPort;
    private AtomicBoolean state = new AtomicBoolean(false);
    private volatile SerialDeviceNotAvailableException initError;

    private SerialPort getSerialPort(byte portNumber) {
        try {
            SerialPort instance = relayPorts.get((int) portNumber);
            if (instance != null && instance.isOpened()) {
                return instance;
            }

            instance = new SerialPort(this.getPortStr());

            instance.openPort();
            instance.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            relayPorts.put((int) portNumber, instance);

            return instance;
        } catch (SerialPortException ex) {
            this.initError = new SerialDeviceNotAvailableException(
                    "Error while opening serial port: " + this.getAddress(), ex);

            return null;
        }
    }

    public RelaySerialDevice(byte portNumber, byte channel) {
        super(portNumber);
        this.channel = channel;
        this.serialPort = getSerialPort(portNumber);
    }

    @Override
    public void checkAvailable() throws SerialDeviceNotAvailableException {
        if (initError != null) {
            throw initError;
        }

        byte[] checkCmd = CommandBuilder.INSTANCE_4CH.getControlCommand(ControlCommand.READING_STATUS, channel);
        try {
            serialPort.writeBytes(checkCmd);
            byte[] response = serialPort.readBytes(8, READ_TIMEOUT);

            if (!Arrays.equals(CommandBuilder.READING_STATE_RESPONSE, response)) {
                throw new SerialDeviceNotAvailableException("Device response error: " + this.getAddress());
            }
        } catch (SerialPortException ex) {
            throw new SerialDeviceNotAvailableException("Device available error: " + this.getAddress(), ex);
        } catch (SerialPortTimeoutException ex) {
            throw new SerialDeviceNotAvailableException("Device read timeout error: " + this.getAddress(), ex);
        }
    }

    @Override
    public String getLabel() {
        return "Relay device";
    }

    @Override
    public String getDescription() {
        return "Relay at " + this.getAddress() + ", channel: " + channel;
    }

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Actuator;
    }

    @Override
    public Boolean getState() throws DeviceException {
        return state.get();
    }

    @Override
    public void setState(Boolean state) throws DeviceException {
        try {
            ControlCommand controlCmd = (state) ? ControlCommand.OPEN : ControlCommand.CLOSE;
            CommandReturn returnCmd = (state) ? CommandReturn.OPEN : CommandReturn.CLOSE;

            byte[] cmd = CommandBuilder.INSTANCE_4CH.getControlCommand(controlCmd, channel);
            this.serialPort.writeBytes(cmd);

            byte[] actualResp = serialPort.readBytes(8, READ_TIMEOUT);
            byte[] expectedResp = CommandBuilder.INSTANCE_4CH.getReturnCommand(returnCmd, channel);

            if (!Arrays.equals(actualResp, expectedResp)) {
                throw new DeviceException("Device response error " + this.getDescription());
            }

            this.state.set(state);
        } catch (SerialPortException | SerialPortTimeoutException ex) {
            throw new DeviceException("Error while changing state of device: " + this.getDescription(), ex);
        }
    }

    @Override
    public ActuatorType getActuatorType() {
        return ActuatorType.switchActuator;
    }

    @Override
    public void close() {
        Collection<SerialPort> ports = relayPorts.values();
        for (SerialPort port : ports) {
            try {
                port.closePort();
            } catch (SerialPortException ex) {
                throw new IllegalStateException("Cannot close port: " + serialPort.getPortName(), ex);
            }
        }
        relayPorts.clear();
    }

    @Override
    public String toString() {
        return this.getDescription();
    }

    @Override
    public String getAddress() {
        return super.getAddress() + ";" + this.channel;
    }

    @Override
    public void closeOpened() {
        if (this.serialPort != null && this.serialPort.isOpened()) {
            try {
                this.serialPort.closePort();
            } catch (SerialPortException ex) {
                throw new IllegalStateException("Cannot close port: " + serialPort.getPortName(), ex);
            }
        }
    }
}
