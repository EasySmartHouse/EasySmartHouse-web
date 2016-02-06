/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.mocks.network;


import by.ginger.smarthome.network.exception.ConversionException;
import by.ginger.smarthome.network.exception.NetworkException;
import by.ginger.smarthome.network.extension.ConversionExtension;
import by.ginger.smarthome.network.predicate.NetworkSearchSignalingPredicate;
import by.ginger.smarthome.provider.device.Device;
import by.ginger.smarthome.provider.device.DeviceType;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mirash
 */
public class MockNetworkManagerTest {

    private static final MockNetworkManager instance = new MockNetworkManager();

    @BeforeClass
    public static void setUpClass() {
        List<Device> devices = MockNetworkHelper.createSimpleDevicesModel();
        instance.setDevices(devices);
    }

    @Before
    public void setUp() {
        try {
            instance.startSession();
        } catch (NetworkException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @After
    public void tearDown() {
        try {
            instance.endSession();
        } catch (NetworkException ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * Test of refreshDevices method, of class MockNetworkManager.
     */
    @Test
    public void testRefreshDevices() throws Exception {
        System.out.println("***** refreshDevices *****");
        instance.refreshDevices();
    }

    /**
     * Test of search method, of class MockNetworkManager.
     */
    @Test
    public void testSearch() throws Exception {
        System.out.println("***** search ******");
        NetworkSearchSignalingPredicate predicate = new NetworkSearchSignalingPredicate();
        predicate.setDeviceType(DeviceType.Sensor);

        List result = instance.search(predicate);
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    /**
     * Test of getDevices method, of class MockNetworkManager.
     */
    @Test
    public void testGetDevices() throws Exception {
        System.out.println("**** getDevices *****");
        List result = instance.getDevices();
        assertNotNull(result);
        assertEquals(5, result.size());
    }

    /**
     * Test of getDevicesAddresses method, of class MockNetworkManager.
     */
    @Test
    public void testGetDevicesAddresses() throws Exception {
        System.out.println("****** getDevicesAddresses *******");
        List result = instance.getDevicesAddresses();
        assertNotNull(result);
        assertEquals(5, result.size());
    }

    /**
     * Test of isDevicePresent method, of class MockNetworkManager.
     */
    @Test
    public void testIsDevicePresent() throws Exception {
        System.out.println("***** isDevicePresent *****");

        String address = "345673635666";
        boolean expResult = true;
        boolean result = instance.isDevicePresent(address);
        assertEquals(expResult, result);

        address = "7778865444";
        expResult = false;
        result = instance.isDevicePresent(address);
        assertEquals(expResult, result);
    }

    /**
     * Test of getConversionExtension method, of class MockNetworkManager.
     */
    @Test
    public void testGetConversionExtension() {
        System.out.println("**** getConversionExtension *****");
        ConversionExtension result = instance.getConversionExtension();
        assertNotNull(result);
        try {
            result.convert();
        } catch (ConversionException ex) {
            fail(ex.getMessage());
        }
    }
}