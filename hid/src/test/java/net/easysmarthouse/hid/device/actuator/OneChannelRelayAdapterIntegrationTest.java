/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.hid.device.actuator;


import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mirash
 */
public class OneChannelRelayAdapterIntegrationTest {
    
    private static final long TOGGLE_DELAY = 2000;
    private static final OneChannelRelayAdapter instance = new OneChannelRelayAdapter((byte) 0, (short) 0);

    public OneChannelRelayAdapterIntegrationTest() {
    }

    @AfterClass
    public static void tearDownClass() {
        instance.close();
    }

    /**
     * Test of getState method, of class OneChannelRelayAdapter.
     */
    @Test
    public void testGetState() throws Exception {
        System.out.println("**** getState ******");
        Boolean result = instance.getState();
        assertNotNull(result);
    }

    /**
     * Test of setState method, of class OneChannelRelayAdapter.
     */
    @Test
    public void testSetState() throws Exception {
        System.out.println("**** setState *****");
        boolean primary = instance.getState();
        instance.setState(!primary);
        assertTrue(instance.getState() == !primary);
        Thread.sleep(TOGGLE_DELAY);
        instance.setState(primary);
    }
}