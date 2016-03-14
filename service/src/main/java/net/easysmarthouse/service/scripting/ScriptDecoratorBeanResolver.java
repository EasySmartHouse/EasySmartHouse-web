/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.service.scripting;

import net.easysmarthouse.network.NetworkManager;
import net.easysmarthouse.network.NetworkManagerAware;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 *
 * @author rusakovich
 */
public class ScriptDecoratorBeanResolver implements BeanPostProcessor {

    private NetworkManager proxiedNetworkManager;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof NetworkManagerAware) {
            NetworkManagerAware nmAware = (NetworkManagerAware) bean;
            nmAware.setNetworkManager(proxiedNetworkManager);
        }

        return bean;
    }

    public void setProxiedNetworkManager(NetworkManager proxiedNetworkManager) {
        this.proxiedNetworkManager = proxiedNetworkManager;
    }

}
