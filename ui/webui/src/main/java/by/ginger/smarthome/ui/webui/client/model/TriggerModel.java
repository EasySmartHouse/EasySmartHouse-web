/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.model;

import by.ginger.smarthome.provider.device.trigger.Trigger;
import by.ginger.smarthome.ui.webui.client.rpc.BaseCallback;
import by.ginger.smarthome.ui.webui.client.rpc.TriggerServiceAsync;
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
