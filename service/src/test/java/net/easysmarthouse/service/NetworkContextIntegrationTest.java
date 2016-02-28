/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.service;

import net.easysmarthouse.network.NetworkManager;
import net.easysmarthouse.network.exception.NetworkException;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author mirash
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:network-context-test.xml"})
@ActiveProfiles("mocks")
public class NetworkContextIntegrationTest {

    @Autowired
    @Qualifier("networkManagersHub")
    NetworkManager networkManager;

    @Before
    public void setUp() {
        try {
            networkManager.startSession();
        } catch (NetworkException ex) {
            fail(ex.getMessage());
        }
    }

    @After
    public void setDown() {
        try {
            networkManager.endSession();
        } catch (NetworkException ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testNetworkManager() throws Exception {
        System.out.println("*** test NetworkManager ***");

        assertNotNull(networkManager);
        assertNotNull(networkManager.getDevices());
        assertNotNull(networkManager.getConversionExtension());

        assertNotNull(networkManager.getDevicesAddresses());
        assertFalse(networkManager.getDevicesAddresses().isEmpty());

        List<Long> addrs = networkManager.getDevicesAddresses();
        for (Long addr : addrs) {
            assertNotNull(addr);
        }

        networkManager.refreshDevices();
    }
}
