/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.modules;

import net.easysmarthouse.maxim.network.adapter.AdapterProvider;
import net.easysmarthouse.network.NetworkManager;
import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.provider.device.alarm.SignalingElement;
import net.easysmarthouse.sheduler.task.UpdateSignalingElementTask;
import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;
import javax.annotation.Resource;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author mirash
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-app-context.xml", "classpath:signaling-module-context.xml"})
public class SignalingElementModuleIntegrationTest {

    @Resource
    NetworkManager networkManager;
    @Resource
    AdapterProvider adapterProvider;
    
    
    @Resource
    SignalingElement signalingElement;
    @Resource
    SignalingElementModule signalingModule;

    @Test
    public void testUpdateTask() throws OneWireIOException, OneWireException, NetworkException {
        assertNotNull(signalingModule);

        final DSPortAdapter adapter = adapterProvider.getAdapter();
        String portName = adapter.getPortName();
        adapter.selectPort(portName);
        
        networkManager.refreshDevices();
        
        signalingElement.setAlarm(false);
        
        UpdateSignalingElementTask task = new UpdateSignalingElementTask(signalingElement);
        task.setNetworkManager(networkManager);
        task.execute();
        
        assertFalse(signalingElement.isAlarm());
    }
}
