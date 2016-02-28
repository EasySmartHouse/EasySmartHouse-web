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
public abstract class AbstractDeviceConverter<T> implements DeviceConverter<T> {

    @Override
    public abstract Device getDevice(T t);

    @Override
    public List<Device> getDevices(List<T> objects) {
        final List<Device> devices = new LinkedList<Device>();

        for (T t : objects) {
            final Device device = this.getDevice(t);
            devices.add(device);
        }

        return devices;
    }

}
