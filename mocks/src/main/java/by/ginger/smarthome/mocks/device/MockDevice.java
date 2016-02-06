/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.mocks.device;

import by.ginger.smarthome.provider.device.AbstractDevice;


/**
 *
 * @author mirash
 */
public abstract class MockDevice extends AbstractDevice {

    public void setLabel(String label) {
        this.label = label;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
