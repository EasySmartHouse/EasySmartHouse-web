/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.provider.device.actuator;

/**
 *
 * @author rusakovich
 */
public interface AdjustableActuator extends Actuator<Double> {

    public Double getMinValue();

    public Double getMaxValue();
    
    public Double getDefaultValue();
    
    public Double getChangeStep();

}
