/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.server.auth;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import net.easysmarthouse.ui.webui.client.auth.AuthService;
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
