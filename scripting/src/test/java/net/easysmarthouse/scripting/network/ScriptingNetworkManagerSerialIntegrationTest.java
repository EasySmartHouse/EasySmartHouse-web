/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.network;

import net.easysmarthouse.provider.device.actuator.SwitchActuator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author rusakovich
 */
public class ScriptingNetworkManagerSerialIntegrationTest {

    public ScriptingNetworkManagerSerialIntegrationTest() {
    }

    /**
     * Test of init method, of class ScriptingNetworkManager.
     */
    @Test
    public void test() throws Exception {
        System.out.println("**** init ****");
        ScriptingNetworkManager instance = new ScriptingNetworkManager("file:src/test/resources/network-integration-serial");
        instance.init();
        
        SwitchActuator actuator = (SwitchActuator)instance.getDevices().get(0);
        
        assertFalse(actuator.getState());
        
        actuator.setState(true);
        assertTrue(actuator.getState());
        
        Thread.sleep(3000l);
        
        actuator.setState(false);
        assertFalse(actuator.getState());
        
        assertEquals(1, instance.getDevices().size());
        instance.destroy();
    }

}
