/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.mocks.device.sensor;

import by.ginger.smarthome.provider.device.DeviceType;
import by.ginger.smarthome.provider.device.exception.DeviceException;
import by.ginger.smarthome.provider.device.sensor.SensorType;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mirash
 */
public class MockSensorAbstractFactoryTest {

    /**
     * Test of createMock method, of class AbstractMockSensorFactory.
     */
    @Test
    public void testCreateMock_String_double() throws DeviceException, InterruptedException {
        System.out.println("***** createMock *****");
        String sensor = "humidity";
        double value = 14.0;
        MockSensor result = MockSensorAbstractFactory.createMock(sensor, value);

        assertNotNull(result);
        assertEquals(DeviceType.Sensor, result.getDeviceType());
        assertEquals(SensorType.HumiditySensor, result.getSensorType());

        assertTrue(Math.abs(value - result.getValue()) < 0.01d);

        double value1 = 14.0;
        double value2 = 20.0;
        result = MockSensorAbstractFactory.createMock(sensor, value1, value2);

        assertNotNull(result);
        assertEquals(DeviceType.Sensor, result.getDeviceType());
        assertEquals(SensorType.HumiditySensor, result.getSensorType());

        Thread.sleep(100);
        double resultValue = result.getValue();
        assertTrue(value1 <= resultValue);
        assertTrue(value2 >= resultValue);
    }

    /**
     * Test of createMock method, of class AbstractMockSensorFactory.
     */
    @Test
    public void testCreateMock_3args() throws Exception {
        System.out.println("***** createMock *****");
        String sensor = "temperature";
        double minEdge = 15.0;
        double maxEdge = 20.0;
        MockSensor result = MockSensorAbstractFactory.createMock(sensor, minEdge, maxEdge);

        assertNotNull(result);
        assertEquals(DeviceType.Sensor, result.getDeviceType());
        assertEquals(SensorType.TemperatureSensor, result.getSensorType());

        Thread.sleep(100);
        assertTrue(Math.abs(maxEdge - result.getValue()) <= (maxEdge - minEdge));
    }

    /**
     * Test of createMock method, of class AbstractMockSensorFactory.
     */
    @Test
    public void testCreateMock_4args() throws Exception {
        System.out.println("***** createMock *****");
        String sensor = "pressure";
        double minEdge = 744.0;
        double maxEdge = 789.0;
        long changeDelay = 5000L;
        MockSensor result = MockSensorAbstractFactory.createMock(sensor, minEdge, maxEdge, changeDelay);

        assertNotNull(result);
        assertEquals(DeviceType.Sensor, result.getDeviceType());
        assertEquals(SensorType.PressureSensor, result.getSensorType());

        Thread.sleep(100);
        assertTrue(Math.abs(maxEdge - result.getValue()) <= (maxEdge - minEdge));
    }
}