/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.mocks.device.actuator;

import net.easysmarthouse.mocks.device.MockDevice;
import net.easysmarthouse.provider.device.DeviceType;
import net.easysmarthouse.provider.device.actuator.ActuatorType;
import net.easysmarthouse.provider.device.actuator.SwitchActuator;
import net.easysmarthouse.provider.device.exception.DeviceException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author mirash
 */
public class MockSwitchActuator extends MockDevice implements SwitchActuator {

    private AtomicBoolean currentState = new AtomicBoolean();
    
    public MockSwitchActuator(){
    }
    
    public MockSwitchActuator(boolean initialState){
        this();
        currentState.set(initialState);
    }

    @Override
    public Boolean getState() throws DeviceException {
        return currentState.get();
    }

    @Override
    public void setState(Boolean state) throws DeviceException {
        currentState.set(state);
    }

    @Override
    public ActuatorType getActuatorType() {
        return ActuatorType.switchActuator;
    }

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Actuator;
    }
}
