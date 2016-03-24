/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.serial.device.actuator;

/**
 *
 * @author rusakovich
 */
public class Relay4ChannelsDeviceDiscovery extends Relay2ChannelsDeviceDiscovery {

    private static final int MAX_CHANNELS_COUNT = 4;

    @Override
    public int getChannelsCount() {
        return MAX_CHANNELS_COUNT;
    }

}
