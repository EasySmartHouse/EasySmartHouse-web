/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.maxim.device.actuator;

import by.ginger.smarthome.maxim.device.OneWireContainerAdapter;
import by.ginger.smarthome.provider.device.DeviceType;
import by.ginger.smarthome.provider.device.actuator.ActuatorType;
import by.ginger.smarthome.provider.device.actuator.SwitchActuator;
import by.ginger.smarthome.provider.device.exception.DeviceException;
import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.container.OneWireContainer;
import com.dalsemi.onewire.container.SwitchContainer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author rusakovich
 */
public class SwitchContainerAdapter extends OneWireContainerAdapter implements SwitchActuator {

    private static final Log log = LogFactory.getLog(SwitchContainerAdapter.class);

    private static final int CHANNEL_NUMBER = 0;

    private final SwitchContainer switchContainer;
    private final boolean smartOn;

    public SwitchContainerAdapter(SwitchContainer switchContainer) {
        super((OneWireContainer) switchContainer);
        this.switchContainer = switchContainer;
        //Checks to see if the channels of this switch support 'smart on'. 
        //Smart on is the ability to turn on a channel such that only 1-Wire 
        //device on this channel are awake and ready to do an operation. 
        //This greatly reduces the time to discover the device down a branch. 
        //If this method returns true then the method
        //setLatchState(int,boolean,boolean,byte[]) can be used 
        //with the doSmart parameter true.
        smartOn = switchContainer.hasSmartOn();
    }

    @Override
    public Boolean getState() throws DeviceException {
        try {
            byte[] state = switchContainer.readDevice();
            return switchContainer.getLatchState(CHANNEL_NUMBER, state);
        } catch (OneWireException ex) {
            log.error("Switch state reading error", ex);
            throw new DeviceException(ex);
        }
    }

    @Override
    public void setState(Boolean switchState) throws DeviceException {
        try {
            byte[] state = switchContainer.readDevice();
            //Sets the latch state of the indicated channel. 
            //The method writeDevice() must be called to finalize 
            //changes to the device. Note that multiple 'set' 
            //methods can be called before one call to writeDevice().
            switchContainer.setLatchState(CHANNEL_NUMBER, switchState, smartOn, state);
            switchContainer.writeDevice(state);
        } catch (OneWireException ex) {
            log.error("Switch state reading error", ex);
            throw new DeviceException(ex);
        }
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
