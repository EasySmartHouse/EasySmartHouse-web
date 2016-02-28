/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client;

import net.easysmarthouse.ui.webui.client.controller.RootController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gwtmvc.client.MvcEntryPoint;

/**
 * Main entry point.
 *
 * @author mirash
 */
public class WebuiEntryPoint extends MvcEntryPoint {

    /**
     * Creates a new instance of MainEntryPoint
     */
    public WebuiEntryPoint() {
        super(new RootController());
    }

    @Override
    protected void showPeripherals() {
    }

    @Override
    protected void hideLoadingIndicator() {
        RootPanel.get("loading-Message").setVisible(false);
    }

    @Override
    public void onUncaughtException(Throwable e) {
        Window.alert(e.getMessage());
    }
}
