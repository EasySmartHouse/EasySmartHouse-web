/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.network;

import by.ginger.smarthome.network.predicate.NetworkSearchPredicate;
import by.ginger.smarthome.network.exception.NetworkException;
import by.ginger.smarthome.network.extension.ConversionExtension;
import by.ginger.smarthome.provider.device.Device;
import java.util.List;

/**
 *
 * @author rusakovich
 */
public interface NetworkManager {

    /**
     * Initialization network
     */
    public void init();

    /**
     * Close and destroy network
     */
    public void destroy();

    /**
     * Begin exclusive usage of network
     *
     * @throws by.ginger.smarthome.network.exception.NetworkException
     */
    public void startSession() throws NetworkException;

    /**
     * End exclusive usage of network.
     *
     * @throws by.ginger.smarthome.network.exception.NetworkException
     */
    public void endSession() throws NetworkException;

    /**
     * When need to update devices list
     *
     * @throws by.ginger.smarthome.network.exception.NetworkException
     */
    public void refreshDevices() throws NetworkException;

    /**
     * Searching device by criteria
     *
     * @param predicate
     * @return devices list
     * @throws by.ginger.smarthome.network.exception.NetworkException
     */
    public List<Device> search(NetworkSearchPredicate predicate) throws NetworkException;

    /**
     *
     * @return all devices in network
     * @throws by.ginger.smarthome.network.exception.NetworkException
     */
    public List<Device> getDevices() throws NetworkException;

    /**
     *
     * @return all devices addresses
     * @throws by.ginger.smarthome.network.exception.NetworkException
     */
    public List<Long> getDevicesAddresses() throws NetworkException;

    /**
     * Checks if given device is present
     *
     * @param address
     * @return
     * @throws by.ginger.smarthome.network.exception.NetworkException
     */
    public boolean isDevicePresent(String address) throws NetworkException;

    /**
     * Common conversion operation extension
     *
     * @return<code>ConversionExtension</code>
     */
    public ConversionExtension getConversionExtension();

}
