/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.serial.util;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jssc.SerialPort;
import jssc.SerialPortException;

/**
 *
 * @author rusakovich
 */
public class SerialPortHelper {

    private static final Logger log = Logger.getLogger(SerialPortHelper.class.getName());
    private final static Pattern COM_PORT_PATTERN = Pattern.compile("^COM\\d{1,2}$");

    private SerialPortHelper() {
    }

    public static void initPort(SerialPort port, int baudRate, int dataBits, int stopBits, int parity) {
        try {
            if (!port.isOpened()) {
                port.openPort();
            }

            port.setParams(baudRate, dataBits, stopBits, parity);

        } catch (SerialPortException ex) {
            log.severe(ex.getMessage());
        }
    }

    public static void closePort(SerialPort port) {
        try {
            if (port.isOpened()) {
                port.closePort();
            }

        } catch (SerialPortException ex) {
            log.severe(ex.getMessage());
        }
    }

    public static int getPortNumber(String portName) {
        Matcher portMatcher = COM_PORT_PATTERN.matcher(portName);
        if (portMatcher.matches()) {
            return Integer.valueOf(portName.replaceFirst("COM", ""));
        }
        return -1;
    }

}
