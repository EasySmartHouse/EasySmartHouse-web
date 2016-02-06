/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.network;

import by.ginger.smarthome.network.predicate.NetworkSearchPredicate;
import by.ginger.smarthome.network.exception.NetworkException;
import by.ginger.smarthome.network.extension.CompositeConversionExtension;
import by.ginger.smarthome.network.extension.ConversionExtension;
import by.ginger.smarthome.provider.device.Device;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author mirash
 */
public class NetworkManagersHub implements NetworkManager, NetworkManagerStorage {

    private List<NetworkManager> networks = new CopyOnWriteArrayList<NetworkManager>();
    private volatile ConversionExtension extension;

    public NetworkManagersHub(List<NetworkManager> networks) {
        this.networks = networks;
    }

    public NetworkManagersHub() {
    }

    @Override
    public void startSession() throws NetworkException {
        for (NetworkManager networkManager : networks) {
            networkManager.startSession();
        }
    }

    @Override
    public void endSession() throws NetworkException {
        for (NetworkManager networkManager : networks) {
            networkManager.endSession();
        }
    }

    @Override
    public void refreshDevices() throws NetworkException {
        for (NetworkManager networkManager : networks) {
            networkManager.refreshDevices();
        }
    }

    @Override
    public List<Device> search(NetworkSearchPredicate predicate) throws NetworkException {
        List<Device> found = new ArrayList<>();

        for (NetworkManager networkManager : networks) {
            found.addAll(networkManager.search(predicate));
        }

        return found;
    }

    @Override
    public List<Device> getDevices() throws NetworkException {
        List<Device> allDevices = new ArrayList<>();

        for (NetworkManager networkManager : networks) {
            allDevices.addAll(networkManager.getDevices());
        }

        return allDevices;
    }

    @Override
    public List<Long> getDevicesAddresses() throws NetworkException {
        List<Long> addresses = new ArrayList<>();

        for (NetworkManager networkManager : networks) {
            addresses.addAll(networkManager.getDevicesAddresses());
        }

        return addresses;
    }

    @Override
    public boolean isDevicePresent(String address) throws NetworkException {
        for (NetworkManager networkManager : networks) {
            if (networkManager.isDevicePresent(address)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ConversionExtension getConversionExtension() {
        if (this.extension == null) {
            synchronized (this) {
                CompositeConversionExtension compositeExtension = new CompositeConversionExtension();
                for (NetworkManager networkManager : networks) {
                    compositeExtension.add(networkManager.getConversionExtension());
                }
                this.extension = compositeExtension;
            }
        }
        return this.extension;
    }

    @Override
    public void add(NetworkManager networkManager) {
        this.networks.add(networkManager);
    }

    @Override
    public void init() {
        for (NetworkManager networkManager : networks) {
            networkManager.init();
        }
    }

    @Override
    public void destroy() {
        for (NetworkManager networkManager : networks) {
            networkManager.destroy();
        }
    }
}
