/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.maxim.network.adapter;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;
import javax.annotation.Resource;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author mirash
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-app-context.xml"})
public class AdapterProviderIntegrationTest {

    @Resource
    AdapterProvider adapterProvider;

    @Test
    public void testAdapterProviderInit() throws OneWireIOException, OneWireException {
        assertNotNull(adapterProvider);
        
        DSPortAdapter adapter = adapterProvider.getAdapter();
        
        final String adapterName = adapter.getAdapterName();
        final String portName = adapter.getPortName();
        
        assertEquals("{DS9490}", adapterName);
        assertEquals("USB1", portName);
    }
}