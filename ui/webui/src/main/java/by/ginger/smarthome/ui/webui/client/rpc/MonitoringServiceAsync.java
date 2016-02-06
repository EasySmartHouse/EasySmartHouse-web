/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.rpc;

import by.ginger.smarthome.provider.device.sensor.Sensor;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author rusakovich
 */
public interface MonitoringServiceAsync {

    public void getSensors(AsyncCallback<List<Sensor>> asyncCallback);

}
