/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.zigbee.device.sensors;

import java.util.concurrent.atomic.AtomicReference;
import net.easysmarthouse.provider.device.DeviceType;
import net.easysmarthouse.provider.device.exception.DeviceException;
import net.easysmarthouse.provider.device.sensor.Sensor;
import net.easysmarthouse.provider.device.sensor.SensorType;
import net.easysmarthouse.zigbee.device.AbstractZigbeeDevice;

/**
 *
 * @author rusakovich
 */
public class ZigbeeTemperatureSensor extends AbstractZigbeeDevice implements Sensor {
    
    private final AtomicReference<Double> valueRef = new AtomicReference<Double>(new Double(0));
    
    public ZigbeeTemperatureSensor(String address) {
        super(address);
    }
    
    @Override
    public double getValue() throws DeviceException {
        return valueRef.get();
    }
    
    @Override
    public SensorType getSensorType() {
        return SensorType.TemperatureSensor;
    }
    
    @Override
    public String getLabel() {
        return String.format("ZigBee temperature sensor [%s]", getAddress());
    }
    
    @Override
    public String getDescription() {
        return getLabel();
    }
    
    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Sensor;
    }
    
    @Override
    public void close() {
    }
    
    public void setValue(double value) {
        valueRef.set(value);
    }
    
}
