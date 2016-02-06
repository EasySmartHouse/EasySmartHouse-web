/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.rpc;

import by.ginger.smarthome.provider.device.sensor.Sensor;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;

/**
 *
 * @author rusakovich
 */
@RemoteServiceRelativePath("monitoring")
public interface MonitoringService extends RemoteService {

    public List<Sensor> getSensors();

}
