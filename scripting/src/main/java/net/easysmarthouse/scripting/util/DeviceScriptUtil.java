/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author rusakovich
 */
public class DeviceScriptUtil {

    private static final String ADDRESS_PATTERN = "address:\\s*'(.*)'";

    private DeviceScriptUtil() {
    }

    public static String getDeviceAddress(String scriptContent) {
        Pattern pattern = Pattern.compile(ADDRESS_PATTERN);
        Matcher matcher = pattern.matcher(scriptContent);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

}
