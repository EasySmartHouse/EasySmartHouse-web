/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.el.util;

import java.util.regex.Pattern;

/**
 *
 * @author mirash
 */
public class NumberUtils {

    private static final String decimalPattern = "([0-9]*)\\.([0-9]*)";

    private NumberUtils() {
    }

    public static boolean isNumber(String numberStr) {
        if (numberStr == null || numberStr.trim().isEmpty()) {
            return false;
        }

        return Pattern.matches(decimalPattern, numberStr);
    }
}
