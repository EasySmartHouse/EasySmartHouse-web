/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.mocks.device.key;

import net.easysmarthouse.mocks.device.MockDevice;
import net.easysmarthouse.provider.device.DeviceType;

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
