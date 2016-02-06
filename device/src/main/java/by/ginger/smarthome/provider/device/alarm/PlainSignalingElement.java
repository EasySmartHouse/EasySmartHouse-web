/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.provider.device.alarm;

import by.ginger.smarthome.provider.device.AbstractDevice;
import by.ginger.smarthome.provider.device.DeviceType;

/**
 *
 * @author rusakovich
 */
public class PlainSignalingElement extends AbstractDevice implements SignalingElement {

    private static final long serialVersionUID = 1L;
    private boolean enabled;
    private boolean alarm;
    private long id;
    private String keyAddress;

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isAlarm() {
        return alarm;
    }

    @Override
    public void setAlarm(boolean alarm) {
        this.alarm = alarm;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getKeyAddress() {
        return keyAddress;
    }

    public void setKeyAddress(String keyAddress) {
        this.keyAddress = keyAddress;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Key;
    }
}
