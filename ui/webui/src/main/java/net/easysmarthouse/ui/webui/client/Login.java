/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client;

import net.easysmarthouse.ui.webui.client.auth.LoginRequest;
import net.easysmarthouse.ui.webui.client.bundle.LoginBundle;
import net.easysmarthouse.ui.webui.client.messages.MessagesHolder;
import net.easysmarthouse.ui.webui.client.util.MessageFormat;
import net.easysmarthouse.ui.webui.client.view.widget.ErrorMessage;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 *
 * @author mirash
 */
public class Login extends Composite {

    private static final int ERRORS_MAX_COUNT = 3;
    private static final int LOGIN_CONSTRAINT_SIZE = 5;
    private static final int PASSWORD_CONSTRAINT_SIZE = 5;
    private static LoginUiBinder uiBinder = LoginUiBinder.INSTANCE;
    private static net.easysmarthouse.ui.i18n.Login messages = MessagesHolder.getInstance().getLoginMessages();
    @UiField(provided = true)
    final LoginBundle res;
    @UiField
    TextBox loginBox;
    @UiField
    TextBox passwordBox;
    @UiField
    HTMLPanel errors;
    private Boolean loginInvalid = false;
    private Boolean pwdInvalid = false;

    public Login() {
        this.res = GWT.create(LoginBundle.class);
        res.style().ensureInjected();
        initWidget(uiBinder.createAndBindUi(this));
    }

    private void showError(String header, String msg) {
        if (errors.getWidgetCount() >= ERRORS_MAX_COUNT) {
            errors.clear();
        }
        errors.add(new ErrorMessage(header, msg));
    }

    @UiHandler("buttonSubmit")
    void doClickSubmit(ClickEvent event) {
        if (loginInvalid || pwdInvalid) {
            showError(messages.errorMessageHeader(), messages.errorMessageLoginOrPasswordTooShort());
            return;
        }

        String login = loginBox.getText();
        String password = passwordBox.getText();

        LoginRequest loginRequest = new LoginRequest(login, password, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {

                    int statusCode = response.getStatusCode();
                    if (statusCode != Response.SC_UNAUTHORIZED
                            && statusCode != Response.SC_OK) {
                        onError(request, new RequestException(response.getStatusText()
                                + ":\n" + response.getText()));
                        return;
                    }

                    if (response.getStatusCode() == Response.SC_UNAUTHORIZED) {
                        onError(request, new RequestException(
                                messages.errorMessageLoginOrPasswordIncorrect()));
                    } else {
                        UrlBuilder current = Window.Location.createUrlBuilder();
                        PageDispatcher.getInstance().assign(current.buildString(), Page.LOGIN, Page.WEBUI);
                    }
                }

                @Override
                public void onError(Request request, Throwable throwable) {
                    if (errors.getWidgetCount() > 0) {
                        errors.clear();
                    }
                    showError(messages.errorMessageHeader(), throwable.getMessage());
                }
            });

    }

    @UiHandler("loginBox")
    void handleLoginChange(ValueChangeEvent<String> event) {
        if (event.getValue().length() < LOGIN_CONSTRAINT_SIZE) {
            String msg = MessageFormat.format(
                    messages.errorMessageLoginTooShort(), LOGIN_CONSTRAINT_SIZE);
            showError(messages.errorMessageHeader(), msg);
            loginInvalid = true;
        } else {
            loginInvalid = false;
        }
    }

    @UiHandler("passwordBox")
    void handlePasswordChange(ValueChangeEvent<String> event) {
        if (event.getValue().length() < PASSWORD_CONSTRAINT_SIZE) {
            String msg = MessageFormat.format(
                    messages.errorMessagePasswordTooShort(), PASSWORD_CONSTRAINT_SIZE);
            showError(messages.errorMessageHeader(), msg);
            pwdInvalid = true;
        } else {
            pwdInvalid = false;
        }
    }
}
