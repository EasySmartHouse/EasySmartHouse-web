/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.cameras.device.alarm;

import by.ginger.smarthome.cameras.core.WebcamUsbDeviceFilter;
import com.github.sarxos.webcam.Webcam;
import org.junit.Test;

/**
 *
 * @author mirash
 */
public class MotionDetectorElementIntegrationTest {

    private static final int INTERVAL = 100; // ms

    public MotionDetectorElementIntegrationTest() {
    }

    /**
     * Test of isAlarm method, of class MotionDetectorElement.
     */
    @Test
    public void testIsAlarm() throws Exception {
        System.out.println("****** isAlarm *******");
                
        Webcam webcam = WebcamUsbDeviceFilter.getWebcams().get(0);
        System.out.println(webcam.getName());
        
        MotionDetectorElement instance = new MotionDetectorElement(webcam);

        while (true) {

            if (instance.isAlarm()){
                System.out.println("Motion detected!");
            }
            
            try {
                Thread.sleep(INTERVAL * 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }
}