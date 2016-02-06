/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.rpc;

import by.ginger.smarthome.ui.i18n.Errors;
import by.ginger.smarthome.ui.webui.client.messages.MessagesHolder;
import by.ginger.smarthome.ui.webui.client.view.js.BootstrapDialog;
import by.ginger.smarthome.ui.webui.client.view.js.BootstrapDialogParams;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author rusakovich
 * @param <T>
 */
public abstract class BaseCallback<T> implements AsyncCallback<T> {
    
    private final Errors messages = MessagesHolder.getInstance().getErrorsMessages();
    
    @Override
    public void onFailure(Throwable caught) {
        BootstrapDialogParams params = BootstrapDialogParams.create();
        params.setTitle(messages.errorDialogTitle());
        params.setMessage(caught.toString());
        
        BootstrapDialogParams.Button cancelButton = params.new Button("cancelBtn");
        cancelButton.setLabel(messages.errorDialogButtonCloseTitle());
        cancelButton.setAction(BootstrapDialog.createCloseFunction());
        
        BootstrapDialogParams.Button[] buttons = new BootstrapDialogParams.Button[1];        
        buttons[0] = cancelButton;
        params.setButtons(buttons);
        
        BootstrapDialog.get().show(params);
        
    }
    
}
