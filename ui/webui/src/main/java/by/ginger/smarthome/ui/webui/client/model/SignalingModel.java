/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.model;

import by.ginger.smarthome.provider.device.alarm.SignalingElement;
import by.ginger.smarthome.ui.webui.client.rpc.BaseCallback;
import by.ginger.smarthome.ui.webui.client.rpc.SignalingServiceAsync;
import java.util.List;

/**
 *
 * @author mirash
 */
public class SignalingModel extends ChangeableModel<List<SignalingElement>> {

    private SignalingServiceAsync signalings;

    @Override
    protected void init() {
        super.init();
        signalings = serviceLocator.getSignalings();
    }

    @Override
    void updateModel() {
        signalings.getSignalingElements(new BaseCallback<List<SignalingElement>>() {
            @Override
            public void onSuccess(List<SignalingElement> result) {
                delegateOnSuccess(result);
            }
        });
    }
    
}
