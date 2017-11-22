/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.zigbee.network;

import java.util.List;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import net.easysmarthouse.network.NetworkManager;
import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.network.predicate.NetworkSearchSimplePredicate;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.converter.DeviceConverter;
import net.easysmarthouse.provider.device.sensor.Sensor;
import net.easysmarthouse.zigbee.device.sensors.ZigbeeTemperatureSensor;
import net.easysmarthouse.zigbee.util.ZigbeeFrame;
import org.apache.log4j.Logger;

/**
 *
 * @author rusakovich
 */
public class ZigbeeCoordinatorListener implements SerialPortEventListener {

    private static final Logger LOG = Logger.getLogger(ZigbeeCoordinatorListener.class.getName());

    private DeviceConverter deviceConverter;

    private final NetworkManager networkManager;
    private final SerialPort serialPort;

    public ZigbeeCoordinatorListener(NetworkManager networkManager, SerialPort serialPort) {
        this.serialPort = serialPort;
        this.networkManager = networkManager;
    }

    private void updateDevice(final ZigbeeFrame frame) throws NetworkException {
        NetworkSearchSimplePredicate predicate = new NetworkSearchSimplePredicate();
        predicate.setAddress(frame.getSourceAddress());

        List<Device> foundDevices = networkManager.search(predicate);
        for (Device device : foundDevices) {
            switch (device.getDeviceType()) {
                case Sensor:
                    Sensor sensor = (Sensor) device;
                    switch (sensor.getSensorType()) {
                        case TemperatureSensor: {
                            ZigbeeTemperatureSensor zigbeeTempSensor = (ZigbeeTemperatureSensor) sensor;
                            zigbeeTempSensor.setValue(frame.getTemperatureData());
                            break;
                        }
                        default:
                            throw new UnsupportedOperationException("Unsupported sensor type: " + sensor.getSensorType());
                    }
                    break;
                default:
                    throw new UnsupportedOperationException("Unknown device: " + device.getAddress());
            }
        }
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR() && event.getEventValue() > 0) {
            try {
                byte[] receivedData = serialPort.readBytes(ZigbeeFrame.FRAME_SIZE);
                ZigbeeFrame frame = new ZigbeeFrame(receivedData);
                if (networkManager.isDevicePresent(frame.getSourceAddress())) {
                    updateDevice(frame);
                } else {
                    Device newDevice = deviceConverter.getDevice(frame);
                    networkManager.getDevices().add(newDevice);
                }
            } catch (SerialPortException ex) {
                LOG.error("Error in receiving string from COM-port: " + serialPort.getPortName(), ex);
            } catch (NetworkException ex) {
                LOG.error("Zigbee network exception", ex);
            }
        }
    }

    public void setDeviceConverter(DeviceConverter deviceConverter) {
        this.deviceConverter = deviceConverter;
    }

}
