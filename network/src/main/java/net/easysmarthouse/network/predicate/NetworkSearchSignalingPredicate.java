/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.network.predicate;

import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.alarm.SignalingElement;

/**
 *
 * @author rusakovich
 */
public class NetworkSearchSignalingPredicate<D extends Device> extends NetworkSearchSimplePredicate<D> {

    private boolean signaling;

    @Override
    public boolean apply(D device) {
        boolean devicePredicate = super.apply(device);
        if (!devicePredicate) {
            return false;
        }

        if (device instanceof SignalingElement) {
            SignalingElement signalingElement = (SignalingElement) device;
            return (signalingElement.isAlarm() == signaling);
        }

        return true;
    }

    public boolean isSignaling() {
        return signaling;
    }

    public void setSignaling(boolean signaling) {
        this.signaling = signaling;
    }
}
