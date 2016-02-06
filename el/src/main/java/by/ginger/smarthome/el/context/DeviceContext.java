/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.el.context;

import by.ginger.smarthome.network.NetworkManager;
import by.ginger.smarthome.provider.device.Device;

/**
 *
 * @author mirash
 */
public interface DeviceContext {
    
    public Device getDevice(String name);
    
    public NetworkManager getNetworkManager();
    
}
