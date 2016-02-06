/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.bundle;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.resources.client.CssResource;

/**
 *
 * @author mirash
 */
public interface LoginBundle extends ClientBundle {

    /**
     * Login CssResource.
     */
    public interface LoginCss extends CssResource {

        String blackText();

        String redText();

        String loginButton();

        String box();

        String background();
    }

    @Source("Login.css")
    LoginCss style();
}
