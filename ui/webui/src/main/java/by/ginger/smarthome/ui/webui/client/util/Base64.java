/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.util;

/**
 * Base64 MIME content transfer encoding.
 *
 * @see http://en.wikipedia.org/wiki/Base64
 */
public class Base64 {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    // Base-64 pad character "="
    private static final String PAD = "=";
    
    private Base64(){
    }

    /**
     * Convert an array of big-endian words to a base-64 string
     */
    public static String encode(int[] arr, int byteLen) {
        return encode(toByteArray(arr, byteLen));
    }
    private static final int[] BSHIFT = {24, 16, 8, 0};

    protected static byte[] toByteArray(int[] arr, int byteLen) {
        byte[] bytes = new byte[byteLen];
        int l = Math.min(arr.length * 4, byteLen);
        int i = 0;
        for (int b = 0; b < l; b++) {
            bytes[b] = (byte) (arr[i] >> BSHIFT[b & 3] & 0xff);
        }
        return bytes;
    }

    public static String encode(byte[] arr) {
        StringBuilder sb = new StringBuilder();
        int l = arr.length;
        int m = l % 3;
        l -= m;
        for (int i = 0; i < l; i += 3) {
            encodeTriplet(sb, arr, i, 3);
        }
        if (m == 2) {
            encodeTriplet(sb, arr, l, 2);
        } else if (m == 1) {
            encodeTriplet(sb, arr, l, 1);
        }
        return sb.toString();
    }

    private static void encodeTriplet(StringBuilder sb, byte[] array, int index, int len) {
        int triplet = (array[index] & 0xFF) << 16;
        if (len >= 2) {
            triplet |= (array[index + 1] & 0xFF) << 8;
        }
        if (len >= 3) {
            triplet |= (array[index + 2] & 0xFF);
        }
        int pad = 3 - len;
        for (int j = 3; j >= pad; j--) {
            int p = (triplet >> (j * 6)) & 0x3F;
            sb.append(ALPHABET.charAt(p));
        }
        while (pad-- > 0) {
            sb.append(PAD);
        }
    }
}
