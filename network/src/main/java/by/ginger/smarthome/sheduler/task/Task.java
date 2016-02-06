/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.sheduler.task;

import by.ginger.smarthome.network.NetworkManager;

/**
 *
 * @author rusakovich
 */
public interface Task {

    /**
     * Action that should be executed
     */
    public void execute();

    public NetworkManager getNetworkManager();

    public void setNetworkManager(NetworkManager networkManager);

}
