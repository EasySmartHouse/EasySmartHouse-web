/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.server;

import by.ginger.smarthome.provider.device.sensor.Sensor;
import by.ginger.smarthome.provider.device.sensor.SensorModule;
import by.ginger.smarthome.ui.webui.client.rpc.MonitoringService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author rusakovich
 */
public class MonitoringServiceImpl extends ServerBaseServlet implements MonitoringService {

    @Autowired
    SensorModule sensorModule;

    @Override
    public List<Sensor> getSensors() {
        return sensorModule.getDevices();
    }

}
