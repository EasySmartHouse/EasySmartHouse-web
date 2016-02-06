/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.server;

import by.ginger.smarthome.provider.device.actuator.Actuator;
import by.ginger.smarthome.provider.device.actuator.ActuatorsModule;
import by.ginger.smarthome.provider.device.exception.DeviceException;
import by.ginger.smarthome.ui.webui.client.rpc.ActuatorsService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author mirash
 */
public class ActuatorsServiceImpl extends ServerBaseServlet implements ActuatorsService {

    private final Logger log = Logger.getLogger(ActuatorsServiceImpl.class.getName());

    @Autowired
    ActuatorsModule actuatorsModule;

    @Override
    public List<Actuator> getActuators() {
        return actuatorsModule.getDevices();
    }

    @Override
    public void changeState(String address, Double state) {
        try {
            actuatorsModule.changeState(address, state);
        } catch (DeviceException ex) {
            log.error("Cannot change the state of: [" + address + "]", ex);
        }
    }

    @Override
    public void changeState(String address, Boolean state) {
        try {
            actuatorsModule.changeState(address, state);
        } catch (DeviceException ex) {
            log.error("Cannot change the state of: [" + address + "]", ex);
        }
    }
}
