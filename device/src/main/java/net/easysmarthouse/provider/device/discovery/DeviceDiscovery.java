/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.provider.device.discovery;

/**
 *
 * @author rusakovich
 */
public interface DeviceDiscovery<Handler> {

    public boolean isDeviceAvailable(Handler handler);

}
