/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.server.auth;

import java.util.Collections;
import java.util.Map;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author mirash
 */
public class WebAuthenticationProvider implements AuthenticationProvider {

    //TODO db authentication
    protected Map<String, String> users = Collections.EMPTY_MAP;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        if (authentication == null) {
            throw new IllegalStateException("Authentication error");
        }

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        if (users.get(username) == null) {
            throw new UsernameNotFoundException("User not found");
        }

        String storedPass = users.get(username);

        if (!storedPass.equals(password)) {
            throw new BadCredentialsException("Invalid password");
        }

        Authentication customAuthentication =
                new UserAuthentication("ROLE_USER", authentication);
        customAuthentication.setAuthenticated(true);

        return customAuthentication;

    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public void setUsers(Map<String, String> users) {
        this.users = users;
    }
}
