/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.server;

import by.ginger.smarthome.provider.device.exception.DeviceException;
import by.ginger.smarthome.provider.device.trigger.Trigger;
import by.ginger.smarthome.provider.device.trigger.TriggerModule;
import by.ginger.smarthome.ui.webui.client.rpc.TriggerService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author mirash
 */
public class TriggersServiceImpl extends ServerBaseServlet implements TriggerService {

    private final Logger log = Logger.getLogger(TriggersServiceImpl.class.getName());
    @Autowired
    TriggerModule triggerModule;

    @Override
    public List<Trigger> getTriggers() {
        return triggerModule.getDevices();
    }

    @Override
    public void setEnabled(String name, boolean enabled) {
        try {
            triggerModule.setEnabled(name, enabled);
        } catch (DeviceException ex) {
            log.error("Cannot change the state of: [" + name + "]", ex);
        }
    }
}
