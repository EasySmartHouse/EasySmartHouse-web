/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.auth;

import net.easysmarthouse.ui.webui.client.util.Base64;
import net.easysmarthouse.ui.webui.client.util.StringHelper;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.user.client.Window;

/**
 *
 * @author mirash
 */
public class LoginRequest {

    private static final String REQUEST_URL = "/webui/j_spring_security_check";

    public LoginRequest(String username, String password, RequestCallback callback) {

        RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, REQUEST_URL);
        rb.setHeader("Authorization", createBasicAuthToken(username, password));
        rb.setCallback(callback);

        try {
        rb.send();
        } catch (RequestException e) {
            Window.alert(e.getMessage());
    }

    }

    private String createBasicAuthToken(String username, String password) {
        byte[] bytes = StringHelper.stringToBytes(username + ":" + password);
        String token = Base64.encode(bytes);
        return "Basic " + token;
    }
}
