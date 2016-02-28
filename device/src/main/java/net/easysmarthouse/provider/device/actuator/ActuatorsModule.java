/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.provider.device.actuator;

import net.easysmarthouse.provider.device.DevicesModule;
import net.easysmarthouse.provider.device.exception.DeviceException;
import java.util.List;

/**
 *
 * @author rusakovich
 */
public interface ActuatorsModule extends DevicesModule<Actuator> {

    public void changeState(String address, Object state)
            throws DeviceException;
    
    public List<Actuator> getActuators();
}
