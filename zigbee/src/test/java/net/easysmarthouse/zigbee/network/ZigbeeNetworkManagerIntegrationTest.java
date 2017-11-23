/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.zigbee.network;

import java.util.List;
import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.sensor.Sensor;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author rusakovich
 */
public class ZigbeeNetworkManagerIntegrationTest {

    private static final String PORT = "COM5";

    private static ZigbeeNetworkManager instance;

    public ZigbeeNetworkManagerIntegrationTest() {
    }

    @Before
    public void setUp() throws Exception {
        instance.startSession();
        Thread.sleep(3000l);
    }

    @After
    public void tearDown() throws NetworkException {
        instance.endSession();
    }

    @BeforeClass
    public static void beforeClass() {
        instance = new ZigbeeNetworkManager();
        instance.setPort(PORT);
        instance.init();
    }

    @AfterClass
    public static void afterClass() {
        instance.destroy();
    }

    @Test
    public void testGetDevices() throws Exception {
        System.out.println("**** getDevices *****");

        List<Device> devices = instance.getDevices();
        assertTrue(devices.size() > 0);
    }

    @Test
    public void testGetValue() throws Exception {
        System.out.println("***** getValue *****");
        List<Device> devices = instance.getDevices();

        for (Device device : devices) {
            Sensor sensor = (Sensor) device;
            assertTrue(sensor.getValue() > 0.0);

            System.out.println(String.format("Address: %s, value: %4.3f",
                    sensor.getAddress(), sensor.getValue()));
        }

    }

    @Test
    public void testGetDevicesAddresses() throws Exception {
        System.out.println("***** getDeviceAddresses *****");
        List<Long> addresses = instance.getDevicesAddresses();
        for (Long address : addresses) {
            assertTrue(address != null);
            System.out.println(String.format("Address: %d", address));
        }
    }
}
