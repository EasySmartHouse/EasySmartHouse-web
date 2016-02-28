/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.el.parser;

import net.easysmarthouse.el.context.FakeDeviceContext;
import net.easysmarthouse.el.context.FakeNetworkManager;
import net.easysmarthouse.el.expression.Expression;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.DeviceType;
import net.easysmarthouse.provider.device.exception.DeviceException;
import net.easysmarthouse.provider.device.sensor.Sensor;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 *
 * @author mirash
 */
public class SimpleExpressionParserIntegrationTest {

    private FakeDeviceContext deviceContext;
    private Device device1;
    private Sensor sensor1;
    private Sensor sensor2;

    public SimpleExpressionParserIntegrationTest() {
    }

    @Before
    public void setUp() throws DeviceException {
        FakeNetworkManager nm = new FakeNetworkManager();

        device1 = mock(Device.class);
        when(device1.getAddress()).thenReturn("111111111111");
        nm.addDevice(device1);

        sensor1 = mock(Sensor.class);
        when(sensor1.getValue()).thenReturn(1.22d);
        when(sensor1.getDeviceType()).thenReturn(DeviceType.Sensor);
        nm.addDevice(sensor1);

        sensor2 = mock(Sensor.class);
        when(sensor2.getValue()).thenReturn(26.43d);
        when(sensor2.getDeviceType()).thenReturn(DeviceType.Sensor);
        nm.addDevice(sensor2);

        deviceContext = new FakeDeviceContext(nm);
        deviceContext.addDevice("device1", device1);
        deviceContext.addDevice("sensor1", sensor1);
        deviceContext.addDevice("sensor2", sensor2);
    }

    @After
    public void tearDown() {
        deviceContext = null;
    }

    /**
     * Test of parse method, of class SimpleExpressionParser.
     */
    @Test
    public void testParse() throws Exception {
        System.out.println("****** parse *******");
        
        String expressionStr = "$${(device1 present) and ((sensor2>34.44) and (sensor1<3.45))";
        SimpleExpressionParser instance = new SimpleExpressionParser(deviceContext);
        
        Expression result = instance.parse(expressionStr);
        assertFalse((Boolean) result.getValue());

        when(sensor2.getValue()).thenReturn(99.23d);
        assertTrue((Boolean) result.getValue());
    }
}