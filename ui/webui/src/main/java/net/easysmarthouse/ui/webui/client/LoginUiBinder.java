/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author mirash
 */
@UiTemplate("Login.ui.xml")
interface LoginUiBinder extends UiBinder<Widget, Login> {
    
    public static final LoginUiBinder INSTANCE = GWT.create(LoginUiBinder.class);
    
}
