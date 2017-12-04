/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.gateway.sms;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import jssc.SerialPort;
import jssc.SerialPortException;
import net.easysmarthouse.provider.device.gateway.AbstractGateway;
import net.easysmarthouse.serial.util.SerialPortHelper;

/**
 *
 * @author rusakovich
 */
public class SmsNotificationGateway extends AbstractGateway {

    private final ExecutorService gatewayExecutor = Executors.newSingleThreadExecutor();

    private static final byte[] SEND_CMD_PREFIX = {0x3C, 0x00, 0x75, 0x00, 0x75};
    private static final byte[] SEND_CMD_POSTFIX = {0x3C, 0x00, 0x73, 0x00, 0x73};

    private final static int BAUDRATE_DEF = SerialPort.BAUDRATE_9600;
    private final static int DATABITS_DEF = SerialPort.DATABITS_8;
    private final static int STOPBITS_DEF = SerialPort.STOPBITS_1;
    private final static int PARITY_DEF = SerialPort.PARITY_NONE;

    private final String portName;

    private int baudRate = BAUDRATE_DEF;
    private int databits = DATABITS_DEF;
    private int stopbits = STOPBITS_DEF;
    private int parity = PARITY_DEF;

    private SerialPort port;

    public SmsNotificationGateway(String portName) {
        this.portName = portName;
    }

    public void init() {
        this.port = new SerialPort(portName);
        SerialPortHelper.initPort(port, baudRate, databits, stopbits, parity);
    }

    public void close() {
        if (port != null) {
            SerialPortHelper.closePort(port);
        }
    }

    @Override
    public void send(final String message) throws IOException {
        if (message == null || message.length() == 0) {
            throw new IllegalArgumentException("Sending message must not be null!");
        }

        gatewayExecutor.submit(new Callable<Void>() {

            @Override
            public Void call() throws Exception {
                byte[] messageContent = message.getBytes(StandardCharsets.US_ASCII);

                byte[] resultCmd = new byte[SEND_CMD_PREFIX.length + messageContent.length + SEND_CMD_POSTFIX.length];

                System.arraycopy(SEND_CMD_PREFIX, 0, resultCmd, 0, SEND_CMD_PREFIX.length);
                System.arraycopy(messageContent, 0, resultCmd, SEND_CMD_PREFIX.length, messageContent.length);
                System.arraycopy(SEND_CMD_POSTFIX, 0, resultCmd, messageContent.length, SEND_CMD_POSTFIX.length);

                try {
                    port.writeBytes(resultCmd);
                    return null;
                } catch (SerialPortException ex) {
                    throw new IOException(ex);
                }
            }

        });

    }

    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    public void setDatabits(int databits) {
        this.databits = databits;
    }

    public void setStopbits(int stopbits) {
        this.stopbits = stopbits;
    }

    public void setParity(int parity) {
        this.parity = parity;
    }

}
