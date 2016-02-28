/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.view.handler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;

/**
 *
 * @author mirash
 */
public class LogoutHandler implements ClickHandler {

    private static final String LOGOUT_PAGE_DEFAULT = "logout";
    private String logoutPage = LOGOUT_PAGE_DEFAULT;
    
    public LogoutHandler(){
    }

    public LogoutHandler(String logoutPage) {
        this();
        this.logoutPage = logoutPage;
    }

    @Override
    public void onClick(ClickEvent event) {
        Window.Location.assign(GWT.getHostPageBaseURL() + logoutPage);
    }
}
