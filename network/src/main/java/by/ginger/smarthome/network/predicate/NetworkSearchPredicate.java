/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.network.predicate;

import by.ginger.smarthome.provider.device.Device;

/**
 *
 * @author rusakovich
 */
public interface NetworkSearchPredicate<D extends Device> {
    
    boolean apply(D device);

}
