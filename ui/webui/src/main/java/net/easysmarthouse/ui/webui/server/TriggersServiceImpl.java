/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.server;

import net.easysmarthouse.provider.device.exception.DeviceException;
import net.easysmarthouse.provider.device.trigger.Trigger;
import net.easysmarthouse.provider.device.trigger.TriggerModule;
import net.easysmarthouse.ui.webui.client.rpc.TriggerService;
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
