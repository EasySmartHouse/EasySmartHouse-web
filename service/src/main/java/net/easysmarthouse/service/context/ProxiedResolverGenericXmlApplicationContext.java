/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.service.context;

import java.util.ArrayList;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 *
 * @author rusakovich
 */
public class ProxiedResolverGenericXmlApplicationContext extends GenericXmlApplicationContext {

    public ProxiedResolverGenericXmlApplicationContext() {
        super();
    }

    public ProxiedResolverGenericXmlApplicationContext(Resource[] resources) {
        super(resources);
    }

    public ProxiedResolverGenericXmlApplicationContext(String[] resourceLocations) {
        super(resourceLocations);
    }

    public ProxiedResolverGenericXmlApplicationContext(Class<?> relativeClass, String[] resourceNames) {
        super(relativeClass, resourceNames);

    }

    @Override
    public <T extends Object> T getBean(Class<T> requiredType) throws BeansException {
        Assert.notNull(requiredType, "Required type must not be null");
        String[] beanNames = getBeanNamesForType(requiredType);
        String primaryCandidate = null;
        
        if (beanNames.length > 1) {
            ArrayList<String> autowireCandidates = new ArrayList<String>();

            for (String beanName : beanNames) {
                BeanDefinition beanDefinition = getBeanDefinition(beanName);
                if (beanDefinition.isAutowireCandidate()) {
                    autowireCandidates.add(beanName);
                    if (beanDefinition.isPrimary()) {
                        primaryCandidate = beanName;
                    }
                }
            }

            for (String autowireCandidate : autowireCandidates) {
                if (autowireCandidates.contains(autowireCandidate + "Proxied")) {
                    primaryCandidate = autowireCandidate;
                }
            }

            if (autowireCandidates.size() > 0) {
                beanNames = autowireCandidates.toArray(new String[autowireCandidates.size()]);
            }
        }

        if (beanNames.length == 1) {
            return getBean(beanNames[0], requiredType);

        } else if (beanNames.length > 1) {
            // more than one bean defined, lookup primary candidate
            if (primaryCandidate != null) {
                return getBean(primaryCandidate, requiredType);
            }
            throw new NoSuchBeanDefinitionException(requiredType, "expected single bean but found "
                    + beanNames.length + ": " + StringUtils.arrayToCommaDelimitedString(beanNames));

        } else if (beanNames.length == 0 && getParentBeanFactory() != null) {
            return getParentBeanFactory().getBean(requiredType);

        } else {
            throw new NoSuchBeanDefinitionException(requiredType, "expected single bean but found "
                    + beanNames.length + ": " + StringUtils.arrayToCommaDelimitedString(beanNames));

        }
    }

}
