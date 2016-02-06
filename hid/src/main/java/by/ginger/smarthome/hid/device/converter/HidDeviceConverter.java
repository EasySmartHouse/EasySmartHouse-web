/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.hid.device.converter;

import by.ginger.smarthome.hid.device.HidDescriptor;
import by.ginger.smarthome.hid.device.RelayDescriptor;
import by.ginger.smarthome.hid.device.TemperDescriptor;
import by.ginger.smarthome.hid.device.actuator.OneChannelRelayAdapter;
import by.ginger.smarthome.hid.device.sensors.TemperatureHidDeviceAdapter;
import by.ginger.smarthome.provider.device.Device;
import by.ginger.smarthome.provider.device.converter.AbstractDeviceConverter;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.usb4java.DeviceDescriptor;

/**
 *
 * @author rusakovich
 */
public class HidDeviceConverter extends AbstractDeviceConverter<DeviceDescriptor> {

    private static final Log log = LogFactory.getLog(HidDeviceConverter.class);
    private static final List<HidDescriptor> SUPPORTED_DESC = new LinkedList<>();

    static {
        SUPPORTED_DESC.add(TemperDescriptor.getInstance());
        SUPPORTED_DESC.add(RelayDescriptor.getInstance());
    }

    @Override
    public Device getDevice(DeviceDescriptor deviceDescriptor) {
        HidDescriptor currDesc = new HidDescriptor(
                deviceDescriptor.idProduct(), deviceDescriptor.idVendor());

        if (SUPPORTED_DESC.contains(currDesc)) {
            short serial = deviceDescriptor.iSerialNumber();
            byte port = deviceDescriptor.bDeviceProtocol();

            if (currDesc.equals(TemperDescriptor.getInstance())) {
                return new TemperatureHidDeviceAdapter(port, serial);
            }
            
            if (currDesc.equals(RelayDescriptor.getInstance())) {
                return new OneChannelRelayAdapter(port, serial);
            }

        } else {
            log.warn("Cannot find supported device for descriptor: " + currDesc.toString());
        }

        return null;
    }
}
