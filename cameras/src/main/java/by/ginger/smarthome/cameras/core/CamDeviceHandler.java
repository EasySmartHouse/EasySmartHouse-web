/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.cameras.core;

import java.util.Collection;

/**
 *
 * @author mirash
 */
public interface CamDeviceHandler {

    public void openAvailable();

    public void closeAll();

    public Collection<Integer> getDeviceNumbers();
}
