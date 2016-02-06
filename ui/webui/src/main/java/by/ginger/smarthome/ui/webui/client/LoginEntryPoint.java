/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 *
 * @author mirash
 */
public class LoginEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
        RootPanel.get("content").add(new Login());
    }
}
