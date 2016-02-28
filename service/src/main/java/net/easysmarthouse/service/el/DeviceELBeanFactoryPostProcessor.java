/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.service.el;

import net.easysmarthouse.el.context.DeviceContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanExpressionResolver;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 *
 * @author rusakovich
 */
public class DeviceELBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanExpressionResolver base = beanFactory.getBeanExpressionResolver();
        
        DeviceContext context = new SpringDeviceContext(beanFactory);
        DeviceExpressionResolver deviceELResolver = new DeviceExpressionResolver(context);
        deviceELResolver.setDelegated(base);
        
        beanFactory.setBeanExpressionResolver(deviceELResolver);
    }

}
