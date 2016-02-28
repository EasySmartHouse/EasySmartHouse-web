/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.sheduler.task;

import net.easysmarthouse.network.NetworkManager;

/**
 *
 * @author rusakovich
 */
public abstract class BaseTask implements Task {

    protected NetworkManager networkManager;

    public BaseTask() {
        super();
    }

    @Override
    public NetworkManager getNetworkManager() {
        return networkManager;
    }

    @Override
    public void setNetworkManager(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

}
