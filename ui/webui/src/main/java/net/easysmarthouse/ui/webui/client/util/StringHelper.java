/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.util;

/**
 *
 * @author mirash
 */
public class StringHelper {

    private StringHelper() {
    }

    public static byte[] stringToBytes(String msg) {
        int len = msg.length();
        byte[] bytes = new byte[len];
        for (int i = 0; i < len; i++) {
            bytes[i] = (byte) (msg.charAt(i) & 0xff);
        }
        return bytes;
    }
}
