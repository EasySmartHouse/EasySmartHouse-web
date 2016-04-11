/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.network;

import net.easysmarthouse.network.exception.NetworkException;
import static org.junit.Assert.assertEquals;
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
    public void test() throws NetworkException {
        System.out.println("**** init ****");
        ScriptingNetworkManager instance = new ScriptingNetworkManager("file:src/test/resources/network-integration-serial");
        instance.init();
        assertEquals(1, instance.getDevices().size());
        instance.destroy();
    }

}
