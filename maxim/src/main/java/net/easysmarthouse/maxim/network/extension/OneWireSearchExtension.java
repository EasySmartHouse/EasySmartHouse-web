/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.maxim.network.extension;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.OneWireIOException;
import com.dalsemi.onewire.container.OneWireContainer;
import java.util.List;

/**
 *
 * @author rusakovich
 */
public interface OneWireSearchExtension extends AdapterProviderExtension {

    /**
     * Returns <code>OneWireContainer</code> representation of device with
     * network address <code>address</code>.
     *
     * @return <code>OneWireContainer</code> instance, or <code>null</code> if
     * device not present.
     */
    public OneWireContainer getDevice(long address);

    /**
     * Check if device with given address present int network.
     */
    public boolean isDevicePresent(long address);

    /**
     * Returns list of all devices in network.
     */
    public List<OneWireContainer> getDevices();

    /**
     * Do network search.
     */
    public void refresh() throws OneWireIOException, OneWireException;

    /**
     * Returns a list of all devices addresses.
     */
    public List<Long> getDevicesAddresses();

}
