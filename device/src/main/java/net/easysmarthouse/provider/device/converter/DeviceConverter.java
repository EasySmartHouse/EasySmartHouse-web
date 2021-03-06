/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.provider.device.converter;

import net.easysmarthouse.provider.device.Device;
import java.util.List;

/**
 *
 * @author rusakovich
 */
public interface DeviceConverter<Handler> {

    public Device getDevice(Handler handler);

    public List<Device> getDevices(List<Handler> handler);

}
