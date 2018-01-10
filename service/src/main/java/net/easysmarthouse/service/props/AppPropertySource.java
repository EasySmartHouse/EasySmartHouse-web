/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.service.props;

import java.io.File;
import java.io.IOException;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;

/**
 *
 * @author mirash
 */
public class AppPropertySource extends PropertySource {

    private static final String SOURCE_LOCATION = "file:config" + File.separator + "application.properties";
    private static final String SOURCE_NAME = "application.property.source";
    private PropertySource delegate;

    private void initPropertySource() {
        try {
            this.delegate = new ResourcePropertySource(SOURCE_LOCATION);
        } catch (IOException ex) {
            throw new RuntimeException("application.properties read error", ex);
        }
    }

    public AppPropertySource() {
        super(SOURCE_NAME);
        initPropertySource();
    }

    @Override
    public Object getProperty(String string) {
        return delegate.getProperty(string);
    }
}
