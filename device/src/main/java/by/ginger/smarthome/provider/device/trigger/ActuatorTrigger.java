/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.provider.device.trigger;

/**
 *
 * @author mirash
 */
public interface ActuatorTrigger {
    
    public String getActuatorAddress();
    
    public Object getActuatorValue();
    
}
