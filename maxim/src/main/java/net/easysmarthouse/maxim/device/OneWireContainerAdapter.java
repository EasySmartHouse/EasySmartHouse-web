/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.maxim.device;

import net.easysmarthouse.provider.device.Device;
import com.dalsemi.onewire.container.OneWireContainer;

/**
 *
 * @author rusakovich
 */
public abstract class OneWireContainerAdapter implements Device {

    private final OneWireContainer container;

    public OneWireContainerAdapter(OneWireContainer container) {
        this.container = container;
    }

    @Override
    public String getAddress() {
        return this.container.getAddressAsString();
    }

    @Override
    public String getLabel() {
        return this.container.getName();
    }

    @Override
    public String getDescription() {
        return this.container.getDescription();
    }

}
