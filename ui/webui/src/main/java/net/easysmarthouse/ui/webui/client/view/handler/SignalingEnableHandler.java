/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.view.handler;

import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.alarm.SignalingElement;
import net.easysmarthouse.ui.webui.client.rpc.BaseCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;

/**
 *
 * @author rusakovich
 */
public class SignalingEnableHandler extends BaseHandler implements ClickHandler {

    private final SignalingElement signaligElement;

    public SignalingEnableHandler(SignalingElement signaligElement) {
        this.signaligElement = signaligElement;
    }

    @Override
    public void onClick(ClickEvent event) {
        CheckBox checkBox = (CheckBox) event.getSource();
        boolean enabled = checkBox.getValue();

        String address = null;
        if (signaligElement instanceof Device) {
            Device signaligDevice = (Device) signaligElement;
            address = signaligDevice.getAddress();
        }

        if (address == null) {
            return;
        }

        AsyncCallback<Void> idleCallback = new BaseCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
            }
        };

        this.serviceLocator.getSignalings().setEnabled(address, enabled, idleCallback);

    }

}
