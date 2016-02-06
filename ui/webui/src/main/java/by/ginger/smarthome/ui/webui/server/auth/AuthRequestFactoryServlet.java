/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.server.auth;

import com.google.web.bindery.requestfactory.server.DefaultExceptionHandler;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;

/**
 *
 * @author mirash
 */
public class AuthRequestFactoryServlet extends RequestFactoryServlet {

    public AuthRequestFactoryServlet() {
        super(new DefaultExceptionHandler(), new SecurityDecorator());
    }
}
