/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.provider.device.trigger;

import by.ginger.smarthome.provider.device.exception.DeviceException;

/**
 *
 * @author rusakovich
 */
public interface TriggerCondition {

    public Boolean getResult() throws DeviceException;
}
