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
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author rusakovich
 */
public class ZigbeeNetworkManagerIntegrationTest {

    private ZigbeeNetworkManager instance;

    public ZigbeeNetworkManagerIntegrationTest() {
        instance = new ZigbeeNetworkManager();
        instance.setPort("COM5");
        instance.init();
    }

    @Before
    public void setUp() throws NetworkException {
        instance.startSession();
    }

    @After
    public void tearDown() throws NetworkException {
        instance.endSession();
    }

    @Test
    public void testGetDevices() throws Exception {
        System.out.println("**** getDevices *****");
        List<Device> devices = instance.getDevices();
        Thread.sleep(3000l);
        assertTrue(devices.size() > 0);

        Sensor sensor = (Sensor) devices.get(0);
        assertTrue(sensor.getValue() > 0.0);
    }

}
