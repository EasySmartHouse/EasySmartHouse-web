/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.zigbee.util;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rusakovich
 */
public class ZigbeeFrameTest {

    private static final byte[] FRAME_DATA = {(byte) 0xFE, (byte) 0x0A, (byte) 0x46, (byte) 0x87,
        (byte) 0x70, (byte) 0x79, (byte) 0x02, (byte) 0x00,
        (byte) 0x04, (byte) 0x00, (byte) 0x13, (byte) 0x1D,
        (byte) 0x00, (byte) 0x00, (byte) 0xCA};

    public ZigbeeFrameTest() {
    }

    /**
     * Test of getFrameStart method, of class ZigbeeFrame.
     */
    @Test
    public void testGetFrameStart() {
        System.out.println("***** getFrameStart *****");
        ZigbeeFrame instance = new ZigbeeFrame(FRAME_DATA);
        String expResult = "FE0A4687";
        String result = instance.getFrameStart();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSourceAddress method, of class ZigbeeFrame.
     */
    @Test
    public void testGetSourceAddress() {
        System.out.println("***** getSourceAddress *****");
        ZigbeeFrame instance = new ZigbeeFrame(FRAME_DATA);
        String expResult = "7970";
        String result = instance.getSourceAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTemperatureData method, of class ZigbeeFrame.
     */
    @Test
    public void testGetTemperatureData() {
        System.out.println("***** getTemperatureData *****");
        ZigbeeFrame instance = new ZigbeeFrame(FRAME_DATA);
        byte expResult = (byte) 0x13;
        byte result = instance.getTemperatureData();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVoltageData method, of class ZigbeeFrame.
     */
    @Test
    public void testGetVoltageData() {
        System.out.println("***** getVoltageData *****");
        ZigbeeFrame instance = new ZigbeeFrame(FRAME_DATA);
        byte expResult = (byte) 0x1D;
        byte result = instance.getVoltageData();
        assertEquals(expResult, result);
    }

    /**
     * Test of getParentDevicesAddress method, of class ZigbeeFrame.
     */
    @Test
    public void testGetParentDevicesAddress() {
        System.out.println("*****  getParentDevicesAddress *****");
        ZigbeeFrame instance = new ZigbeeFrame(FRAME_DATA);
        String expResult = "0000";
        String result = instance.getParentDevicesAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFCSCalculation method, of class ZigbeeFrame.
     */
    @Test
    public void testGetFCSCalculation() {
        System.out.println("***** getFCSCalculation *****");
        ZigbeeFrame instance = new ZigbeeFrame(FRAME_DATA);
        byte expResult = (byte) 0xCA;
        byte result = instance.getFCSCalculation();
        assertEquals(expResult, result);
    }

}
