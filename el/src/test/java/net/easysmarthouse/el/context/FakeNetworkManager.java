/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.el.context;

import net.easysmarthouse.network.NetworkManager;
import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.network.extension.ConversionExtension;
import net.easysmarthouse.network.predicate.NetworkSearchPredicate;
import net.easysmarthouse.provider.device.Device;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author mirash
 */
public class FakeNetworkManager implements NetworkManager {
    
    private List<Device> devices = new LinkedList<>();
    
    @Override
    public void startSession() throws NetworkException {
    }
    
    @Override
    public void endSession() throws NetworkException {
    }
    
    @Override
    public void refreshDevices() throws NetworkException {
    }
    
    @Override
    public List<Device> search(NetworkSearchPredicate predicate) throws NetworkException {
        return Collections.EMPTY_LIST;
    }
    
    @Override
    public List<Device> getDevices() throws NetworkException {
        return devices;
    }
    
    @Override
    public List<Long> getDevicesAddresses() throws NetworkException {
        return Collections.EMPTY_LIST;
    }
    
    @Override
    public boolean isDevicePresent(String address) throws NetworkException {
        for (Device device : devices) {
            if (device.getAddress().equalsIgnoreCase(address)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public ConversionExtension getConversionExtension() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void addDevice(Device device) {
        devices.add(device);
    }

    @Override
    public void init() {
    }

    @Override
    public void destroy() {
    }
}
