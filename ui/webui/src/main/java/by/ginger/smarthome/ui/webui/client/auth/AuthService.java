/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.auth;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *
 * @author mirash
 */
@RemoteServiceRelativePath("auth")
public interface AuthService extends RemoteService {
    
    public Boolean isAuthenticated();

    public String getUsername();
}
