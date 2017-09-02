/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.server.auth;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import com.yubico.client.v2.VerificationResponse;
import com.yubico.client.v2.YubicoClient;
import com.yubico.client.v2.exceptions.YubicoValidationFailure;
import com.yubico.client.v2.exceptions.YubicoVerificationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author rusakovich
 */
public class YubicoAuthenticationProvider extends WebAuthenticationProvider implements InitializingBean {

    private Integer clientId;
    private String apiKey;

    private YubicoClient client;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication == null) {
            throw new IllegalStateException("Authentication error");
        }

        String username = (String) authentication.getPrincipal();
        String otp = (String) authentication.getCredentials();

        if (users.get(username) == null) {
            throw new UsernameNotFoundException("User not found");
        }

        try {
            VerificationResponse response = client.verify(otp);
            if (response.isOk()) {
                String yubikeyId = YubicoClient.getPublicId(otp);
                if (!users.get(username).contains(yubikeyId)) {
                    throw new BadCredentialsException("Invalid OTP");
                }
            } else {
                throw new BadCredentialsException("OTP verify error");
            }
        } catch (YubicoVerificationException | YubicoValidationFailure ex) {
            throw new BadCredentialsException(ex.getMessage());
        }

        Authentication customAuthentication
                = new UserAuthentication("ROLE_USER", authentication);
        customAuthentication.setAuthenticated(true);

        return customAuthentication;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.client = YubicoClient.getClient(clientId, apiKey);
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

}
