/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.provider.device.alarm;

/**
 *
 * @author rusakovich
 */
public interface SignalingElement {
    
    public String getKeyAddress();
    
    public boolean isAlarm();

    public void setAlarm(boolean enabled);

    public boolean isEnabled();

    public void setEnabled(boolean enabled);
}
