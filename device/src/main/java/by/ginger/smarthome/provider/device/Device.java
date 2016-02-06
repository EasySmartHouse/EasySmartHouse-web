/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.ginger.smarthome.provider.device;

import java.io.Serializable;

/**
 *
 * @author rusakovich
 */
public interface Device extends Serializable{

    /**
     * Retrieve device unique address. 
     */
    public String getAddress();
        
    /**
     * Return device label. Device label is a string that associate with this
     * device.
     */
    public String getLabel();


    /**
     * Get text description of this device
     */
    public String getDescription();

    /**
     * Get type of this device.
     * 
     * @return object of <code>DeviceType</code> class that represent type of
     *         this device
     */
    public DeviceType getDeviceType();
}
