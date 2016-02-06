/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.network.util;

/**
 *
 * @author rusakovich
 */
public final class AddressHelper {

    private AddressHelper() {
    }

    public static String toString(long address) {
        String shortString = Long.toHexString(address).toUpperCase();
        String string = "0000000000000000" + shortString;
        return string.substring(string.length() - 16);
    }

    public static long toLong(String address) {
        long result = 0;
        for (int i = 0; i < 8; i++) {
            byte address_byte = (byte) ((Character.digit((address.charAt(i * 2)), 16) << 4) | (Character.digit(address
                    .charAt(i * 2 + 1), 16)));
            result <<= 8;
            result |= (long) (address_byte & 0xFF);
        }
        return result;
    }

    public static long buidldHash(String address, long prime) {
        long h = prime; // prime
        int len = address.length();

        for (int i = 0; i < len; i++) {
            h = 31 * h + address.charAt(i);
        }
        return h;
    }
}
