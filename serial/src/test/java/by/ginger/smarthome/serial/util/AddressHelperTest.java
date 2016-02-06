/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.serial.util;

import by.ginger.smarthome.network.util.AddressHelper;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mirash
 */
public class AddressHelperTest {
    
    public AddressHelperTest() {
    }
    
    /**
     * Test of buidldHash method, of class AddressHelper.
     */
    @Test
    public void testBuidldHash() {
        System.out.println("**** buidldHash ****");
        String address = "COM1;2";
        long result = AddressHelper.buidldHash(address, 127l);
        assertEquals(114706421638l, result);
    }
}