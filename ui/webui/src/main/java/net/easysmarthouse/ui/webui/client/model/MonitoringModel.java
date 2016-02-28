/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.model;

import net.easysmarthouse.provider.device.sensor.Sensor;
import net.easysmarthouse.ui.webui.client.rpc.BaseCallback;
import net.easysmarthouse.ui.webui.client.rpc.MonitoringServiceAsync;
import java.util.List;

/**
 *
 * @author rusakovich
 */
public class MonitoringModel extends ChangeableModel<List<Sensor>> {

    private MonitoringServiceAsync monitoring;

    @Override
    protected void init() {
        super.init();
        monitoring = serviceLocator.getMonitoring();
    }

    @Override
    void updateModel() {
        monitoring.getSensors(new BaseCallback<List<Sensor>>() {
            @Override
            public void onSuccess(List<Sensor> result) {
                delegateOnSuccess(result);
            }
        });
    }
}
