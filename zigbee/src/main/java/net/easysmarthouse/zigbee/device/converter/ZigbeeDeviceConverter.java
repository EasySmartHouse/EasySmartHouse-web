/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.zigbee.device.converter;

import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.converter.AbstractDeviceConverter;
import net.easysmarthouse.serial.util.NumberUtil;
import net.easysmarthouse.zigbee.device.sensors.ZigbeeTemperatureSensor;
import net.easysmarthouse.zigbee.util.ZigbeeFrame;

/**
 *
 * @author rusakovich
 */
public class ZigbeeDeviceConverter extends AbstractDeviceConverter<ZigbeeFrame> {

    private static final byte[] TEMPERATURE_SENSOR_SIGNATURE = {(byte) 0xFE, (byte) 0x0A, (byte) 0x46, (byte) 0x87};
    private static final String TEMPERATURE_SENSOR_SIGN_STR = NumberUtil.toHexStr(TEMPERATURE_SENSOR_SIGNATURE);

    @Override
    public Device getDevice(ZigbeeFrame frame) {
        if (frame == null) {
            throw new IllegalStateException("Frame is null!");
        }

        if (TEMPERATURE_SENSOR_SIGN_STR.equals(frame.getFrameStart())) {
            return new ZigbeeTemperatureSensor(frame.getSourceAddress());
        }

        throw new UnsupportedOperationException("Unsupported device: " + frame.getFrameStart());
    }

}
