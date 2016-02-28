/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.server.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 *
 * @author mirash
 */
public class PropertiesSplitterResolver extends PropertyPlaceholderConfigurer {

    private Map propertiesMap;
    private String propertiesPrefix;
    private String valueNameSuffix;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory,
            Properties props) throws BeansException {
        super.processProperties(beanFactory, props);

        propertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();

            if (keyStr.startsWith(propertiesPrefix)) {
                String valueStr = props.getProperty(keyStr);
                keyStr = keyStr.replaceFirst(propertiesPrefix, "");

                String[] propNameParts = keyStr.split("\\.");

                String username = propNameParts[0];
                String propNameSuffix = propNameParts[1];

                if (propNameSuffix.equalsIgnoreCase(valueNameSuffix)) {
                    propertiesMap.put(username, valueStr);
                }
            }
        }
    }

    public void setValueNameSuffix(String valueSuffix) {
        this.valueNameSuffix = valueSuffix;
    }

    public void setPropertiesPrefix(String propertiesPrefix) {
        this.propertiesPrefix = propertiesPrefix;
    }

    public Map getMap() {
        return propertiesMap;
    }
}
