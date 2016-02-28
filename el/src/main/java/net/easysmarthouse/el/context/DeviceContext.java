/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.el.context;

import net.easysmarthouse.network.NetworkManager;
import net.easysmarthouse.provider.device.Device;

/**
 *
 * @author mirash
 */
public interface DeviceContext {
    
    public Device getDevice(String name);
    
    public NetworkManager getNetworkManager();
    
}
