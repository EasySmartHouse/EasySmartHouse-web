/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.mocks.network;

import by.ginger.smarthome.mocks.device.DeviceObservable;
import by.ginger.smarthome.network.NetworkManager;
import by.ginger.smarthome.network.NetworkManagerStorable;
import by.ginger.smarthome.network.NetworkManagerStorage;
import by.ginger.smarthome.network.exception.NetworkException;
import by.ginger.smarthome.network.extension.ConversionExtension;
import by.ginger.smarthome.network.extension.IdleConversionExtension;
import by.ginger.smarthome.network.predicate.NetworkSearchPredicate;
import by.ginger.smarthome.network.predicate.PredicateVisitor;
import by.ginger.smarthome.network.predicate.SimplePredicateVisitor;
import by.ginger.smarthome.provider.device.Device;
import by.ginger.smarthome.provider.device.DeviceType;
import by.ginger.smarthome.provider.device.alarm.SignalingElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author mirash
 */
public class MockNetworkManager implements NetworkManager, DeviceObservable, NetworkManagerStorable {

    private ConversionExtension conversionExtension = new IdleConversionExtension();
    private final Lock lock = new ReentrantLock();
    private ThreadLocal<Boolean> sessionStarts = new ThreadLocal<Boolean>() {
        private static final boolean INITIAL_STATE = false;

        @Override
        protected Boolean initialValue() {
            return INITIAL_STATE;
        }
    };
    private List<Device> devices = Collections.EMPTY_LIST;

    @Override
    public void startSession() throws NetworkException {
        lock.lock();
        sessionStarts.set(true);
    }

    @Override
    public void endSession() throws NetworkException {
        if (sessionStarts.get()) {
            lock.unlock();
        }
        sessionStarts.set(false);
    }

    private void checkNetworkSession() {
        if (!sessionStarts.get()) {
            throw new IllegalStateException("Session must be acquire first");
        }
    }

    @Override
    public void refreshDevices() throws NetworkException {
        checkNetworkSession();
    }

    @Override
    public List<Device> search(NetworkSearchPredicate predicate) throws NetworkException {
        checkNetworkSession();

        if (predicate == null) {
            throw new IllegalArgumentException("Search criteria must not be null!");
        }

        PredicateVisitor visitor = new SimplePredicateVisitor();

        List<Device> resultDevices = new LinkedList<>();

        String address = visitor.getAddress(predicate);
        boolean addressCondition = address != null;

        String label = visitor.getLabel(predicate);
        boolean labelCondition = label != null;

        DeviceType type = visitor.getDeviceType(predicate);
        boolean typeCondition = type != null && type!= DeviceType.Unknown;

        boolean signaling = visitor.getSignaling(predicate);

        for (Device device : devices) {
            if (addressCondition) {
                if (!device.getAddress().equalsIgnoreCase(address)) {
                    continue;
                }
            }

            if (labelCondition) {
                if (!device.getLabel().equalsIgnoreCase(label)) {
                    continue;
                }
            }

            if (typeCondition) {
                if (device.getDeviceType() != type) {
                    continue;
                }
            }

            if (device instanceof SignalingElement) {
                SignalingElement signalingElement = (SignalingElement) device;
                if (signalingElement.isEnabled()) {
                    if (signalingElement.isAlarm() != signaling) {
                        continue;
                    }
                }
            }

            resultDevices.add(device);
        }

        return resultDevices;
    }

    @Override
    public List<Device> getDevices() throws NetworkException {
        checkNetworkSession();
        return devices;
    }

    private Long getDeviceAddress(String address) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null!");
        }

        return Long.valueOf(address.hashCode());
    }

    @Override
    public List<Long> getDevicesAddresses() throws NetworkException {
        checkNetworkSession();
        List<Long> addresses = new ArrayList<>();

        for (Device device : devices) {
            Long address = this.getDeviceAddress(device.getAddress());
            addresses.add(address);
        }

        return addresses;
    }

    @Override
    public boolean isDevicePresent(String address) throws NetworkException {
        checkNetworkSession();
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address must not be empty!");
        }

        for (Device device : devices) {
            if (device.getAddress().equals(address)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public ConversionExtension getConversionExtension() {
        return conversionExtension;
    }

    public void setConversionExtension(ConversionExtension conversionExtension) {
        this.conversionExtension = conversionExtension;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public void addDevice(Device device) {
        if (devices == Collections.EMPTY_LIST) {
            return;
        }

        if (devices.contains(device)) {
            return;
        }

        devices.add(device);
    }

    @Override
    public void removeDevice(Device device) {
        if (devices == Collections.EMPTY_LIST) {
            return;
        }

        if (device == null) {
            return;
        }

        if (!devices.contains(device)) {
            throw new IllegalStateException("No such device [" + device.getAddress() + "]");
        }

        devices.remove(device);
    }

    @Override
    public void setStorage(NetworkManagerStorage storage) {
        storage.add(this);
    }

    @Override
    public void init() {
    }

    @Override
    public void destroy() {
    }
}
