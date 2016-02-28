/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.serial.network;

import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.actuator.SwitchActuator;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author mirash
 */
public class SerialNetworkManagerIntegrationTest {

    SerialNetworkManager instance = new SerialNetworkManager();

    public SerialNetworkManagerIntegrationTest() {
    }

    @Before
    public void setUp() throws NetworkException {
        instance.startSession();
    }

    @After
    public void tearDown() throws NetworkException {
        instance.endSession();
    }

    /**
     * Test of startSession method, of class SerialNetworkManager.
     */
    @Test
    public void testStartSession() throws Exception {
        System.out.println("**** startSession ****");
        instance.startSession();
    }

    /**
     * Test of endSession method, of class SerialNetworkManager.
     */
    @Test
    public void testEndSession() throws Exception {
        System.out.println("**** endSession ****");
        instance.endSession();
    }

    /**
     * Test of refreshDevices method, of class SerialNetworkManager.
     */
    @Test
    public void testRefreshDevices() throws Exception {
        System.out.println("**** refreshDevices ****");
        instance.refreshDevices();
    }

    /**
     * Test of getDevices method, of class SerialNetworkManager.
     */
    @Test
    public void testGetDevices() throws Exception {
        System.out.println("**** getDevices *****");
        List<Device> result = instance.getDevices();
        assertEquals(2, result.size());
        
        for (Device device : result) {
            if (device instanceof SwitchActuator){
                SwitchActuator actuator = (SwitchActuator)device;
                actuator.setState(true);
                Thread.sleep(1000);
                actuator.setState(false);
            }
        }
    }

    /**
     * Test of getDevicesAddresses method, of class SerialNetworkManager.
     */
    @Test
    public void testGetDevicesAddresses() throws Exception {
        System.out.println("*** getDevicesAddresses ***");
        List result = instance.getDevicesAddresses();
        assertEquals(2, result.size());
    }

    /**
     * Test of isDevicePresent method, of class SerialNetworkManager.
     */
    @Test
    public void testIsDevicePresent() throws Exception {
        System.out.println("*** isDevicePresent ***");
        String address = "COM10;0";
        boolean expResult = true;
        boolean result = instance.isDevicePresent(address);
        assertEquals(expResult, result);
    }

}