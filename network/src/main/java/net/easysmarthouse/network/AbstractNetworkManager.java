/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.network;

import net.easysmarthouse.network.predicate.NetworkSearchPredicate;
import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.provider.device.Device;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author rusakovich
 */
public abstract class AbstractNetworkManager implements NetworkManager {

    protected final List<Device> devices = new CopyOnWriteArrayList<Device>();

    @Override
    public List<Device> search(NetworkSearchPredicate predicate) throws NetworkException {
        List<Device> suitable = new LinkedList<>();

        for (Device device : devices) {
            if (predicate.apply(device)) {
                suitable.add(device);
            }
        }

        return suitable;
    }

}
