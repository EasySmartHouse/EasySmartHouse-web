/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.el.context;

import net.easysmarthouse.network.NetworkManager;
import net.easysmarthouse.provider.device.Device;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mirash
 */
public class FakeDeviceContext implements DeviceContext {

    private Map<String, Device> devices = new HashMap<>();
    private NetworkManager networkManager;

    public FakeDeviceContext(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    @Override
    public Device getDevice(String name) {
        return devices.get(name);
    }

    @Override
    public NetworkManager getNetworkManager() {
        return networkManager;
    }

    public void addDevice(String name, Device device) {
        devices.put(name, device);
    }
}
