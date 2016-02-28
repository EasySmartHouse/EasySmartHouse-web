/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.model;

import net.easysmarthouse.provider.device.trigger.Trigger;
import net.easysmarthouse.ui.webui.client.rpc.BaseCallback;
import net.easysmarthouse.ui.webui.client.rpc.TriggerServiceAsync;
import java.util.List;

/**
 *
 * @author mirash
 */
public class TriggerModel extends ChangeableModel<List<Trigger>> {

    private TriggerServiceAsync triggers;

    @Override
    protected void init() {
        super.init();
        triggers = serviceLocator.getTriggers();
    }

    @Override
    void updateModel() {
        triggers.getTriggers(new BaseCallback<List<Trigger>>() {
            @Override
            public void onSuccess(List<Trigger> result) {
                delegateOnSuccess(result);
            }
        });
    }
}
