/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.serial.device.actuator;

import net.easysmarthouse.serial.device.SerialDeviceNotAvailableException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author mirash
 */
public class RelaySerialDeviceIntegrationTest {

    private static final byte PORT_NUM = (byte) 10;
    private static RelaySerialDevice channel1;
    private static RelaySerialDevice channel2;

    public RelaySerialDeviceIntegrationTest() {
    }

    @BeforeClass
    public static void beforeClass() {
        channel1 = new RelaySerialDevice(PORT_NUM, (byte) 0);
        channel2 = new RelaySerialDevice(PORT_NUM, (byte) 1);
    }

    @AfterClass
    public static void afterClass() {
        if (channel1 != null) {
            channel1.close();
        }
        if (channel2 != null) {
            channel2.close();
        }
    }

    @Before
    public void check() throws SerialDeviceNotAvailableException {
        if (channel1 != null) {
            channel1.checkAvailable();
        }
        if (channel2 != null) {
            channel2.checkAvailable();
        }
    }

    @Test
    public void testSetState() throws Exception {
        System.out.println("***** setState ******");

        channel1.setState(false);
        channel2.setState(false);

        channel1.setState(true);
        channel2.setState(true);
    }

    @Test
    public void testGetState() throws Exception {
        System.out.println("***** getState ******");

        channel1.setState(false);
        channel2.setState(false);

        assertFalse(channel1.getState());
        assertFalse(channel2.getState());

        channel1.setState(true);
        channel2.setState(true);

        assertTrue(channel1.getState());
        assertTrue(channel2.getState());
    }

    @Test
    public void testGetAddress() throws Exception {
        System.out.println("***** getAddress ******");
        assertTrue(channel1.getAddress().matches("^COM\\d{1,2};0$"));
        assertTrue(channel2.getAddress().matches("^COM\\d{1,2};1$"));
    }
}