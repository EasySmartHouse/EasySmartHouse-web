/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.rpc;

/**
 *
 * @author rusakovich
 */
public abstract class ServiceLocator {

    private static volatile ServiceLocator instance;

    // @todo switch between Mock and REST
    private static void initInstance() {
        instance = LocatorFactoryImpl.getInstance().getLocator();
    }

    static {
        initInstance();
    }

    public static synchronized ServiceLocator instance() {
        return instance;
    }

    public abstract MonitoringServiceAsync getMonitoring();
    
    public abstract ActuatorsServiceAsync getActuators();
    
    public abstract SignalingServiceAsync getSignalings();
    
    public abstract TriggerServiceAsync getTriggers();

}
