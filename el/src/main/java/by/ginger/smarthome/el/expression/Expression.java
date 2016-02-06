/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.el.expression;


import by.ginger.smarthome.network.exception.NetworkException;
import by.ginger.smarthome.provider.device.exception.DeviceException;

/**
 *
 * @author mirash
 */
public interface Expression {

    public Object getValue() throws DeviceException, NetworkException;
    
}
