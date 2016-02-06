/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.cameras.core;

import com.github.sarxos.webcam.Webcam;
import java.util.StringTokenizer;

/**
 *
 * @author mirash
 */
public class WebcamHelper {

    private WebcamHelper() {
    }

    public static boolean isUsbDevice(Webcam webcam) {
        if (webcam == null) {
            return false;
        }
        return webcam.getDevice().getName().contains("USB");
    }

    public static int getDeviceNumber(Webcam webcam) {
        if (webcam == null) {
            return -1;
        }
        String name = webcam.getDevice().getName();
        StringTokenizer tokenizer = new StringTokenizer(name, " ");

        String numStr = "0";
        while (tokenizer.hasMoreTokens()) {
            numStr = tokenizer.nextToken();
        }

        return Integer.parseInt(numStr);
    }
}
