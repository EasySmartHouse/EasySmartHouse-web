/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.provider.device.trigger;

import java.io.Serializable;

/**
 *
 * @author rusakovich
 */
public interface Trigger extends Serializable {

    public void setEnabled(Boolean enabled);

    public Boolean isEnabled();

    public String getName();
}
