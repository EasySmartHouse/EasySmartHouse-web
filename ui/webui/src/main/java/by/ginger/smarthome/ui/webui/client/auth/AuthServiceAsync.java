/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.auth;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author mirash
 */
public interface AuthServiceAsync {

    public void getUsername(AsyncCallback<String> callback);

    public void isAuthenticated(AsyncCallback<Boolean> asyncCallback);
}
