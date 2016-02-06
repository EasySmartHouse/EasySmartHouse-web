/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.model;

import by.ginger.smarthome.provider.device.sensor.Sensor;
import by.ginger.smarthome.ui.webui.client.rpc.BaseCallback;
import by.ginger.smarthome.ui.webui.client.rpc.MonitoringServiceAsync;
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
