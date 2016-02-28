/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.server.auth;

import com.google.web.bindery.requestfactory.server.ServiceLayerDecorator;
import java.lang.reflect.Method;

/**
 *
 * @author mirash
 */
public class SecurityDecorator extends ServiceLayerDecorator {

    @Override
    public Object invoke(Method domainMethod, Object... args) {
        //TODO service methods check
        return super.invoke(domainMethod, args);
    }
}
