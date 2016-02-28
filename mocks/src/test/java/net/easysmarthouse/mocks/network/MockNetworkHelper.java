/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.mocks.network;

import net.easysmarthouse.mocks.device.MockDevice;
import net.easysmarthouse.mocks.device.actuator.MockSwitchActuator;
import net.easysmarthouse.mocks.device.sensor.MockSensorAbstractFactory;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.sensor.SensorType;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author mirash
 */
public class MockNetworkHelper {

    private MockNetworkHelper() {
    }

    public static List<Device> createSimpleDevicesModel() {
        List<Device> devices = new LinkedList<>();

        MockDevice switchInBath = new MockSwitchActuator();
        switchInBath.setAddress("111122220000");
        switchInBath.setDescription("Switch in bath");
        switchInBath.setLabel("Bath switch");
        devices.add(switchInBath);

        MockDevice lampInKitchen = new MockSwitchActuator();
        lampInKitchen.setAddress("111155550000");
        lampInKitchen.setDescription("Lamp in kitchen");
        lampInKitchen.setLabel("Kitchen lamp");
        devices.add(switchInBath);

        MockDevice tempSensorInRoom = MockSensorAbstractFactory.createMock(
                SensorType.TemperatureSensor, 26d);
        tempSensorInRoom.setAddress("345678904555");
        tempSensorInRoom.setLabel("Temperature inside");
        tempSensorInRoom.setDescription("Temperature sensor in room");
        devices.add(tempSensorInRoom);

        MockDevice tempOutdoor = MockSensorAbstractFactory.createMock(
                SensorType.TemperatureSensor, 10d, 15d, 5000l);
        tempOutdoor.setAddress("345673635666");
        tempOutdoor.setLabel("Outdoor temperature");
        tempOutdoor.setDescription("Temperature sensor outside");
        devices.add(tempOutdoor);

        MockDevice humidityOutdoor = MockSensorAbstractFactory.createMock(
                SensorType.HumiditySensor, 73d);
        humidityOutdoor.setAddress("99924345677");
        humidityOutdoor.setLabel("Outdoor humidity");
        humidityOutdoor.setDescription("Humidity sensor outside");
        devices.add(humidityOutdoor);

        return devices;
    }
}
