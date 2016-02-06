/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.view.widget;

import by.ginger.smarthome.ui.webui.client.auth.AuthAware;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;

/**
 *
 * @author mirash
 */
public class WelcomeLabel extends HTML implements AuthAware {

    private final SafeHtmlBuilder builder;

    public WelcomeLabel(String welcomeText) {
        builder = new SafeHtmlBuilder();
        this.buildContent(welcomeText);
    }

    private void addUsername(String name) {
        builder.appendHtmlConstant("<b>")
                .appendEscaped(name)
                .appendHtmlConstant("</b>");
        this.setHTML(builder.toSafeHtml());
    }

    private void buildContent(String welcomeText) {
        builder.appendEscaped(welcomeText)
                .appendHtmlConstant(" ");

        AUTH.getUsername(new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                addUsername("");
            }

            @Override
            public void onSuccess(String result) {
                addUsername(result);
            }
        });
    }
}
