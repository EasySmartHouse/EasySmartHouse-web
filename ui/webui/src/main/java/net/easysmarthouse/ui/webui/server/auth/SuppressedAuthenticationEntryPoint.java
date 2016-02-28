/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.server.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
 *
 * @author mirash
 */
public class SuppressedAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    private static final String LOGIN_PAGE = "/webui/login.html";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        if (!request.getRequestURL().toString().contains(LOGIN_PAGE)) {
            response.sendRedirect(LOGIN_PAGE);
        }
    }
}
