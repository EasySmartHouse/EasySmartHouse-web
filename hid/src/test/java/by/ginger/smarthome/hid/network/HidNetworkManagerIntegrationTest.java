/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.hid.network;

import by.ginger.smarthome.hid.device.converter.HidDeviceConverter;
import by.ginger.smarthome.network.exception.NetworkException;
import by.ginger.smarthome.network.predicate.NetworkSearchSimplePredicate;
import by.ginger.smarthome.provider.device.Device;
import by.ginger.smarthome.provider.device.DeviceType;
import by.ginger.smarthome.provider.device.actuator.SwitchActuator;
import by.ginger.smarthome.provider.device.sensor.Sensor;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mirash
 */
public class HidNetworkManagerIntegrationTest {

    private static HidNetworkManager instance = new HidNetworkManager();

    static {
        instance.setDeviceConverter(new HidDeviceConverter());
    }

    public HidNetworkManagerIntegrationTest() {
    }

    @BeforeClass
    public static void setUpClass() throws NetworkException {
        instance.startSession();
    }

    @AfterClass
    public static void tearDownClass() throws NetworkException {
        instance.endSession();
    }

    @Before
    public void setUp() throws NetworkException {
    }

    /**
     * Test of refreshDevices method, of class HidNetworkManager.
     */
    @Test
    public void testRefreshDevices() throws Exception {
        System.out.println("***** refreshDevices ******");
        instance.refreshDevices();
    }

    /**
     * Test of search method, of class HidNetworkManager.
     */
    @Test
    public void testSearch() throws Exception {
        System.out.println("**** search ******");
        NetworkSearchSimplePredicate predicate = new NetworkSearchSimplePredicate();
        predicate.setDeviceType(DeviceType.Sensor);
        List<Device> devices = instance.search(predicate);
        assertNotNull(devices);
    }

    /**
     * Test of getDevices method, of class HidNetworkManager.
     */
    @Test
    public void testGetDevices() throws Exception {
        System.out.println("**** getDevices *****");
        List<Device> devices = instance.getDevices();
        assertNotNull(devices);

        for (Device device : devices) {
            assertNotNull(device);

            if (device instanceof Sensor) {
                Sensor sensor = (Sensor) device;
                System.out.println(sensor.toString());
                System.out.println("Value: " + sensor.getValue());
                assertTrue(sensor.getValue() != 0);
            }

            if (device instanceof SwitchActuator) {
                SwitchActuator actuator = (SwitchActuator) device;
                System.out.println(actuator.toString());
                System.out.println("State: " + actuator.getState());
                assertNotNull(actuator.getState());
                
                actuator.setState(Boolean.TRUE);
                Thread.sleep(3000);
                actuator.setState(Boolean.FALSE);
                
            }
        }

    }

    /**
     * Test of getDevicesAddresses method, of class HidNetworkManager.
     */
    @Test
    public void testGetDevicesAddresses() throws Exception {
        System.out.println("*** getDevicesAddresses ****");
        List<Long> result = instance.getDevicesAddresses();
        assertNotNull(result);
        for (Long addr : result) {
            assertNotNull(addr);
            System.out.println(addr);
        }

    }

    /**
     * Test of isDevicePresent method, of class HidNetworkManager.
     */
    @Test
    public void testIsDevicePresent() throws Exception {
        System.out.println("**** isDevicePresent *****");
        boolean result = instance.isDevicePresent("sdfsdferrer");
        assertFalse(result);
    }
}