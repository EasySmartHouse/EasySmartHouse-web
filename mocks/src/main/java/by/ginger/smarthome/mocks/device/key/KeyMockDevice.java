/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.mocks.device.key;

import by.ginger.smarthome.mocks.device.MockDevice;
import by.ginger.smarthome.provider.device.DeviceType;

/**
 *
 * @author mirash
 */
public class KeyMockDevice extends MockDevice {

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Key;
    }
}
