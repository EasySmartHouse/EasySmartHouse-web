/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.mocks.device.actuator;

import by.ginger.smarthome.mocks.device.MockDevice;
import by.ginger.smarthome.provider.device.DeviceType;
import by.ginger.smarthome.provider.device.actuator.ActuatorType;
import by.ginger.smarthome.provider.device.actuator.SwitchActuator;
import by.ginger.smarthome.provider.device.exception.DeviceException;
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
