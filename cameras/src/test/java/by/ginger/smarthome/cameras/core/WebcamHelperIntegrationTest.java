/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.cameras.core;

import com.github.sarxos.webcam.Webcam;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mirash
 */
public class WebcamHelperIntegrationTest {
    
    private static Webcam cam = Webcam.getDefault();
    
    public WebcamHelperIntegrationTest() {
    }
    
    /**
     * Test of isUsbDevice method, of class WebcamHelper.
     */
    @Test
    public void testIsUsbDevice() {
        System.out.println("**** isUsbDevice ****");
        boolean expResult = true;
        boolean result = WebcamHelper.isUsbDevice(cam);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeviceNumber method, of class WebcamHelper.
     */
    @Test
    public void testGetDeviceNumber() {
        System.out.println("**** getDeviceNumber ****");
        Integer result = WebcamHelper.getDeviceNumber(cam);
        assertNotNull(result);
    }
}