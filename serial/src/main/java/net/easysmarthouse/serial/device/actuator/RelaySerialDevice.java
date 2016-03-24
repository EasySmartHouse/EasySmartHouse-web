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
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;
import net.easysmarthouse.serial.util.SerialPortHelper;

/**
 *
 * @author mirash
 */
public class RelaySerialDevice extends AbstractSerialDevice implements SwitchActuator, Closeable {

    private static final int READ_TIMEOUT = 300;
    private final byte channel;
    private final SerialPort serialPort;
    private AtomicBoolean state = new AtomicBoolean(false);

    public RelaySerialDevice(SerialPort serialPort, byte channel) {
        super(SerialPortHelper.getPortNumber(serialPort.getPortName()));
        this.channel = channel;
        this.serialPort = serialPort;
        SerialPortHelper.initPort(serialPort,
                SerialPort.BAUDRATE_9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);
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
        SerialPortHelper.closePort(serialPort);
    }

    @Override
    public String toString() {
        return this.getDescription();
    }

    @Override
    public String getAddress() {
        return super.getAddress() + ";" + this.channel;
    }

}
