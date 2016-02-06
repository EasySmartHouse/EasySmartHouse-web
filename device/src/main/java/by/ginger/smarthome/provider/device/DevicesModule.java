/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.provider.device;

import java.util.List;

/**
 *
 * @author rusakovich
 */
public interface DevicesModule<T> {

    public List<T> getDevices();
    
}
