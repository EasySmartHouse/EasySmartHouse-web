/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.network.predicate;

import net.easysmarthouse.provider.device.DeviceType;

/**
 *
 * @author rusakovich
 */
public class SimplePredicateVisitor implements PredicateVisitor {

    @Override
    public String getAddress(NetworkSearchPredicate predicate) {
        if (predicate instanceof NetworkSearchSimplePredicate) {
            return ((NetworkSearchSimplePredicate) predicate).getAddress();
        }
        return null;
    }

    @Override
    public String getLabel(NetworkSearchPredicate predicate) {
        if (predicate instanceof NetworkSearchSimplePredicate) {
            return ((NetworkSearchSimplePredicate) predicate).getLabel();
        }
        return null;
    }

    @Override
    public DeviceType getDeviceType(NetworkSearchPredicate predicate) {
        if (predicate instanceof NetworkSearchSimplePredicate) {
            return ((NetworkSearchSimplePredicate) predicate).getDeviceType();
        }
        return DeviceType.Unknown;
    }

    @Override
    public boolean getSignaling(NetworkSearchPredicate predicate) {
        if (predicate instanceof NetworkSearchSignalingPredicate) {
            return ((NetworkSearchSignalingPredicate) predicate).isSignaling();
        }
        return false;
    }

}
