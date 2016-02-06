/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.provider.device.actuator;

import by.ginger.smarthome.provider.device.DevicesModule;
import by.ginger.smarthome.provider.device.exception.DeviceException;
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
