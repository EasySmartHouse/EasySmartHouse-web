/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.provider.device.sensor;

import by.ginger.smarthome.provider.device.DevicesModule;
import java.util.List;

/**
 *
 * @author rusakovich
 */
public interface SensorModule extends DevicesModule<Sensor> {
    
    public List<Sensor> getSensors();

}
