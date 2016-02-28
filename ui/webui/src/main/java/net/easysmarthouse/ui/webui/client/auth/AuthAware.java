/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.auth;

import com.google.gwt.core.client.GWT;

/**
 *
 * @author mirash
 */
public interface AuthAware {

    public static final AuthServiceAsync AUTH = GWT.create(AuthService.class);
    
}
