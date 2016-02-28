/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.provider.device.trigger;

import net.easysmarthouse.provider.device.DevicesModule;
import net.easysmarthouse.provider.device.exception.DeviceException;
import java.util.List;

/**
 *
 * @author rusakovich
 */
public interface TriggerModule extends DevicesModule<Trigger>{

    public void setEnabled(String name, Boolean state)
            throws DeviceException;
    
    public List<Trigger> getElements();

}
