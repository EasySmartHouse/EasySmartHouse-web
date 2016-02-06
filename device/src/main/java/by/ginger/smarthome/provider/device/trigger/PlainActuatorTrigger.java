/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.provider.device.trigger;


/**
 *
 * @author rusakovich
 */
public class PlainActuatorTrigger extends BaseTrigger implements ActuatorTrigger {

    private transient String actuatorAddress;
    private transient Object actuatorValue;

    @Override
    public String getActuatorAddress() {
        return this.actuatorAddress;
    }

    @Override
    public Object getActuatorValue() {
        return this.actuatorValue;
    }

    public void setActuatorAddress(String actuatorAddress) {
        this.actuatorAddress = actuatorAddress;
    }

    public void setActuatorValue(Object actuatorValue) {
        this.actuatorValue = actuatorValue;
    }
}
