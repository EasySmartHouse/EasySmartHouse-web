/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.network.predicate;

import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.DeviceType;

/**
 *
 * @author rusakovich
 */
public class NetworkSearchSimplePredicate<D extends Device> implements NetworkSearchPredicate<D> {

    private String address;
    private String label;
    private DeviceType deviceType;

    @Override
    public boolean apply(D device) {
        if (address != null) {
            if (!device.getAddress().equals(address)) {
                return false;
            }
        }

        if (label != null) {
            if (!device.getLabel().equals(label)) {
                return false;
            }
        }

        if (deviceType != null) {
            if (device.getDeviceType() != deviceType) {
                return false;
            }
        }

        return true;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

}
