/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.server;

import net.easysmarthouse.provider.device.sensor.Sensor;
import net.easysmarthouse.provider.device.sensor.SensorModule;
import net.easysmarthouse.ui.webui.client.rpc.MonitoringService;
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
