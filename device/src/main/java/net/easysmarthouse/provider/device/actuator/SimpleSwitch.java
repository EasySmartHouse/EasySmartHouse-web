/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.provider.device.actuator;

import java.io.Serializable;

/**
 *
 * @author rusakovich
 */
public class SimpleSwitch extends AbstractActuator<Boolean> implements SwitchActuator {

    private boolean state;

    public void toggleState() {
        this.state = !state;
    }

    @Override
    public Boolean getState() {
        return this.state;
    }

    @Override
    public void setState(Boolean state) {
        this.state = state;
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
    public ActuatorType getActuatorType() {
        return ActuatorType.switchActuator;
    }
}
