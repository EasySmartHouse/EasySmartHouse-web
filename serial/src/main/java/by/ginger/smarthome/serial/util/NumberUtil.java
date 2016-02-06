/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.serial.util;

/**
 *
 * @author mirash
 */
public class NumberUtil {

    private NumberUtil() {
    }

    public static String toHexStr(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte currByte : bytes) {
            sb.append(String.format("%02X", currByte));
        }
        return sb.toString();
    }
}
