/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.provider.device;

import java.util.List;

/**
 *
 * @author rusakovich
 */
public abstract class AbstractDevice implements Device, DeviceHelper {

    protected String label;
    protected String description;
    protected String address;

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setContainer(List<Device> devices) {
        devices.add(this);
    }
}
