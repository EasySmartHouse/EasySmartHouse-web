/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.server.auth;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import by.ginger.smarthome.ui.webui.client.auth.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author mirash
 */
public class AuthServiceImpl extends RemoteServiceServlet implements AuthService {
    
    private Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public String getUsername() {
        Authentication authentication = getAuthentication();
        
        String username = (authentication != null)
                ? (String) authentication.getPrincipal()
                : null;

        return username;
    }

    @Override
    public Boolean isAuthenticated() {
        return (getAuthentication() != null);
    }
}
