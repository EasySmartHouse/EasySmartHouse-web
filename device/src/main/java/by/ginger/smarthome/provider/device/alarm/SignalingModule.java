/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.provider.device.alarm;

import by.ginger.smarthome.provider.device.DevicesModule;
import by.ginger.smarthome.provider.device.exception.DeviceException;
import java.util.List;

/**
 *
 * @author rusakovich
 */
public interface SignalingModule extends DevicesModule<SignalingElement> {

    public void setEnabled(String address, Boolean state)
            throws DeviceException;
    
    public List<SignalingElement> getElements();
}
