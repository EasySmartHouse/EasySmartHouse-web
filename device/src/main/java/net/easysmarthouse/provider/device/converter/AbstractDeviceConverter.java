/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.provider.device.converter;

import net.easysmarthouse.provider.device.Device;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author rusakovich
 */
public abstract class AbstractDeviceConverter<Handler> implements DeviceConverter<Handler> {

    @Override
    public abstract Device getDevice(Handler handler);

    @Override
    public List<Device> getDevices(List<Handler> objects) {
        final List<Device> devices = new LinkedList<Device>();

        for (Handler handler : objects) {
            final Device device = this.getDevice(handler);
            devices.add(device);
        }

        return devices;
    }
}
