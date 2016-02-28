/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.model;

import net.easysmarthouse.provider.device.actuator.Actuator;
import net.easysmarthouse.ui.webui.client.rpc.ActuatorsServiceAsync;
import net.easysmarthouse.ui.webui.client.rpc.BaseCallback;
import java.util.List;

/**
 *
 * @author mirash
 */
public class ActuatorsModel extends ChangeableModel<List<Actuator>> {

    private ActuatorsServiceAsync actuators;

    @Override
    protected void init() {
        super.init();
        actuators = serviceLocator.getActuators();
    }

    @Override
    void updateModel() {
        actuators.getActuators(new BaseCallback<List<Actuator>>() {
            @Override
            public void onSuccess(List<Actuator> result) {
                delegateOnSuccess(result);
            }
        });
    }
}
