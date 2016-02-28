/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.service.el;

import net.easysmarthouse.el.context.DeviceContext;
import net.easysmarthouse.network.NetworkManager;
import net.easysmarthouse.network.NetworkManagersHub;
import net.easysmarthouse.provider.device.Device;
import org.springframework.beans.factory.BeanFactory;

/**
 *
 * @author rusakovich
 */
public class SpringDeviceContext implements DeviceContext {

    private final BeanFactory beanFactory;

    public SpringDeviceContext(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Device getDevice(String name) {
        return (Device) beanFactory.getBean(name);
    }

    @Override
    public NetworkManager getNetworkManager() {
        return beanFactory.getBean(NetworkManagersHub.class);
    }

}
