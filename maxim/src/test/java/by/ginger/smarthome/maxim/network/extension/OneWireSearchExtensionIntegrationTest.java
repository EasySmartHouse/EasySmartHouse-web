/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.maxim.network.extension;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.OneWireIOException;
import com.dalsemi.onewire.container.OneWireContainer;
import com.dalsemi.onewire.utils.Address;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
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
public class OneWireSearchExtensionIntegrationTest {

    @Resource
    OneWireSearchExtension searchExtension;
    private static final String[] DEVICES = {"FD00000AC4DFE701", "9800000020EC3105", "C2000801AC339F10", "EC000801AC673410"};

    @Before
    public void setUp() throws Throwable {
        searchExtension.refresh();
    }

    @Test
    public void testDevicesSearch() throws OneWireIOException, OneWireException {

        Set<String> expected = new HashSet<String>(Arrays.asList(DEVICES));
        Set<String> actual = new HashSet<String>();

        final List<Long> devices = searchExtension.getDevicesAddresses();
        for (Long address : devices) {
            String addressString = Address.toString(address);
            actual.add(addressString);
        }
        assertEquals(expected, actual);

        final List<OneWireContainer> containers = searchExtension.getDevices();
        actual = new HashSet<String>();
        for (OneWireContainer container : containers) {
            actual.add(container.getAddressAsString());
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testDevicePresent() throws OneWireIOException, OneWireException {
        long address = Address.toLong("FD00000AC4DFE702");
        assertTrue(searchExtension.isDevicePresent(address));
    }
}