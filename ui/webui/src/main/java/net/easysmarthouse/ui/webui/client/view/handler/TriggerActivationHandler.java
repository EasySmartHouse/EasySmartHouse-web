/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.view.handler;

import net.easysmarthouse.provider.device.trigger.Trigger;
import net.easysmarthouse.ui.webui.client.rpc.BaseCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;

/**
 *
 * @author mirash
 */
public class TriggerActivationHandler extends BaseHandler implements ClickHandler {

    private final Trigger trigger;

    public TriggerActivationHandler(Trigger trigger) {
        this.trigger = trigger;
    }

    @Override
    public void onClick(ClickEvent event) {
        CheckBox checkBox = (CheckBox) event.getSource();
        boolean enabled = checkBox.getValue();


        AsyncCallback<Void> idleCallback = new BaseCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
            }
        };

        this.serviceLocator.getTriggers().setEnabled(
                trigger.getName(), enabled, idleCallback);

    }
}
