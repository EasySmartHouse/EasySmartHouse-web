/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.service.el;

import by.ginger.smarthome.el.context.DeviceContext;
import by.ginger.smarthome.network.NetworkManager;
import by.ginger.smarthome.network.NetworkManagersHub;
import by.ginger.smarthome.provider.device.Device;
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
