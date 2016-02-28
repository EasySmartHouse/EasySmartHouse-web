/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.server.auth;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;

/**
 *
 * @author mirash
 */
public class AuthListener implements ApplicationListener<AbstractAuthenticationEvent> {

    private final Logger log = Logger.getLogger(AuthListener.class.getName());

    private String getDescription(AbstractAuthenticationEvent event) {
        StringBuilder builder = new StringBuilder("Authentication event ")
                .append(event.getClass().getSimpleName())
                .append(": ")
                .append(event.getAuthentication().getName())
                .append("; details: ")
                .append(event.getAuthentication().getDetails());

        if (event instanceof AbstractAuthenticationFailureEvent) {
            builder.append("; exception: ")
                    .append(((AbstractAuthenticationFailureEvent) event)
                            .getException().getMessage());
        }

        return builder.toString();
    }

    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent authenticationEvent) {
        String desc = this.getDescription(authenticationEvent);
        log.warn(desc);
    }
}
