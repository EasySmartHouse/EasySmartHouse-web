/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.maxim.network.extension;

import by.ginger.smarthome.maxim.device.sensors.TemperatureContainerAdapter;
import by.ginger.smarthome.maxim.network.adapter.AdapterProvider;
import by.ginger.smarthome.network.extension.ConversionExtension;
import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;
import com.dalsemi.onewire.container.OneWireContainer10;
import javax.annotation.Resource;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
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
public class OneWireConversionExtensionIntegrationTest {

    @Resource
    AdapterProvider adapterProvider;
    @Resource
    ConversionExtension convertExtension;
    private static final double ALLOW_DIFF = 2;
    private DSPortAdapter adapter;
    //TemperatureContainer1
    private OneWireContainer10 device1;
    //TemperatureContainer2
    private OneWireContainer10 device2;
    private TemperatureContainerAdapter temperatureContainerAdapter;

    @Before
    public void setUp() throws Throwable {
        adapter = adapterProvider.getAdapter();

        device1 = (OneWireContainer10) adapter.getDeviceContainer("EC000801AC673410");
        device2 = (OneWireContainer10) adapter.getDeviceContainer("C2000801AC339F10");

        temperatureContainerAdapter = new TemperatureContainerAdapter(device1);
    }

    @Test
    public void doConvertion() throws Throwable {

        double beforeConversion = temperatureContainerAdapter.getValue();

        convertExtension.convert();
        double afterConvertion = readTemperature(device1);

        double readTemperature = readTemperature(device2);
        double diff = readTemperature - afterConvertion;
        assertThat("Difference between two temperatures should be less that 2 celsius", Math.abs(diff) < ALLOW_DIFF, is(true));

        diff = beforeConversion - afterConvertion;
        assertThat("Difference between reader and extension should be less that 2 celsius", Math.abs(diff) < ALLOW_DIFF, is(true));

        temperatureContainerAdapter.setForceConvertion(false);
        diff = temperatureContainerAdapter.getValue() - afterConvertion;
        assertThat("There should be not difference", diff, is(0.0));
    }

    private double readTemperature(OneWireContainer10 device) throws OneWireIOException, OneWireException {
        byte[] state = device.readDevice();
        return device.getTemperature(state);
    }
}