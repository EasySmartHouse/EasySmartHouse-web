/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.messages;

import net.easysmarthouse.ui.i18n.Actuators;
import net.easysmarthouse.ui.i18n.Cameras;
import net.easysmarthouse.ui.i18n.Errors;
import net.easysmarthouse.ui.i18n.Login;
import net.easysmarthouse.ui.i18n.Monitoring;
import net.easysmarthouse.ui.i18n.Root;
import net.easysmarthouse.ui.i18n.Signaling;
import net.easysmarthouse.ui.i18n.Trigger;
import com.google.gwt.core.client.GWT;

/**
 *
 * @author mirash
 */
public class MessagesHolder {

    private final Signaling signalingMessages;
    private final Actuators actuatorsMessages;
    private final Monitoring monitoringMessages;
    private final Root rootMessages;
    private final Login loginMessages;
    private final Cameras camerasMessages;
    private final Errors errorsMessages;
    private final Trigger triggersMessages;

    private MessagesHolder() {
        signalingMessages = GWT.create(Signaling.class);
        actuatorsMessages = GWT.create(Actuators.class);
        monitoringMessages = GWT.create(Monitoring.class);
        rootMessages = GWT.create(Root.class);
        loginMessages = GWT.create(Login.class);
        camerasMessages = GWT.create(Cameras.class);
        errorsMessages = GWT.create(Errors.class);
        triggersMessages = GWT.create(Trigger.class);
    }

    private static class MessagesHolderHelper {

        private static final MessagesHolder INSTANCE = new MessagesHolder();
    }

    public static MessagesHolder getInstance() {
        return MessagesHolderHelper.INSTANCE;
    }

    public Actuators getActuatorsMessages() {
        return actuatorsMessages;
    }

    public Monitoring getMonitoringMessages() {
        return monitoringMessages;
    }

    public Login getLoginMessages() {
        return loginMessages;
    }

    public Root getRootMessages() {
        return rootMessages;
    }

    public Signaling getSignalingMessages() {
        return signalingMessages;
    }

    public Cameras getCamerasMessages() {
        return camerasMessages;
    }

    public Errors getErrorsMessages() {
        return errorsMessages;
    }

    public Trigger getTriggersMessages() {
        return triggersMessages;
    }
}
