/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.cameras.network;

import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.network.extension.ConversionExtension;
import net.easysmarthouse.provider.device.Device;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mirash
 */
public class CamerasNetworkManagerIntegrationTest {

    private static CamerasNetworkManager instance;

    public CamerasNetworkManagerIntegrationTest() {
    }

    @BeforeClass
    public static void setUpClass() throws NetworkException {
        instance = new CamerasNetworkManager();
        instance.startSession();
    }

    @AfterClass
    public static void tearDownClass() throws NetworkException {
        instance.endSession();
    }

    /**
     * Test of refreshDevices method, of class CamerasNetworkManager.
     */
    @Test
    public void testRefreshDevices() throws Exception {
        System.out.println("**** refreshDevices *******");
        instance.refreshDevices();
    }

    /**
     * Test of getDevices method, of class CamerasNetworkManager.
     */
    @Test
    public void testGetDevices() throws Exception {
        System.out.println("***** getDevices *****");
        List<Device> result = instance.getDevices();
        for (Device device : result) {
            assertNotNull(device);
            assertNotNull(device.getAddress());
            System.out.println(device.getAddress());
        }
        assertTrue(result.size() > 0);
    }

    /**
     * Test of getDevicesAddresses method, of class CamerasNetworkManager.
     */
    @Test
    public void testGetDevicesAddresses() throws Exception {
        System.out.println("***** getDevicesAddresses *****");
        List<Long> result = instance.getDevicesAddresses();
        for (Long address : result) {
            System.out.println(address);
            assertNotNull(address);
        }
    }

    /**
     * Test of isDevicePresent method, of class CamerasNetworkManager.
     */
    @Test
    public void testIsDevicePresent() throws Exception { 
        System.out.println("***** isDevicePresent *****");
        boolean expResult = true;
        String address = "USB-2845>CAB@>9AB2> 0";
        boolean result = instance.isDevicePresent(address);
        assertEquals(expResult, result);
    }

    /**
     * Test of getConversionExtension method, of class CamerasNetworkManager.
     */
    @Test
    public void testGetConversionExtension() {
        System.out.println("***** getConversionExtension *****");
        ConversionExtension result = instance.getConversionExtension();
        assertNotNull(result);
    }
}