/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rusakovich
 */
public class DeviceScriptUtilTest {
    
    public DeviceScriptUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDeviceAddress method, of class DeviceScriptUtil.
     */
    @Test
    public void testGetDeviceAddress() {
        System.out.println("**** getDeviceAddress ****");
        String scriptContent = FileHelper.readFile("src/test/resources/sensor1Decorator.js");
        String result = DeviceScriptUtil.getDeviceAddress(scriptContent);
        assertEquals("address", result);
    }
    
}
