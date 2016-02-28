/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.network;

import net.easysmarthouse.network.predicate.NetworkSearchPredicate;
import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.network.extension.ConversionExtension;
import net.easysmarthouse.provider.device.Device;
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
     * @throws net.easysmarthouse.network.exception.NetworkException
     */
    public void startSession() throws NetworkException;

    /**
     * End exclusive usage of network.
     *
     * @throws net.easysmarthouse.network.exception.NetworkException
     */
    public void endSession() throws NetworkException;

    /**
     * When need to update devices list
     *
     * @throws net.easysmarthouse.network.exception.NetworkException
     */
    public void refreshDevices() throws NetworkException;

    /**
     * Searching device by criteria
     *
     * @param predicate
     * @return devices list
     * @throws net.easysmarthouse.network.exception.NetworkException
     */
    public List<Device> search(NetworkSearchPredicate predicate) throws NetworkException;

    /**
     *
     * @return all devices in network
     * @throws net.easysmarthouse.network.exception.NetworkException
     */
    public List<Device> getDevices() throws NetworkException;

    /**
     *
     * @return all devices addresses
     * @throws net.easysmarthouse.network.exception.NetworkException
     */
    public List<Long> getDevicesAddresses() throws NetworkException;

    /**
     * Checks if given device is present
     *
     * @param address
     * @return
     * @throws net.easysmarthouse.network.exception.NetworkException
     */
    public boolean isDevicePresent(String address) throws NetworkException;

    /**
     * Common conversion operation extension
     *
     * @return<code>ConversionExtension</code>
     */
    public ConversionExtension getConversionExtension();

}
