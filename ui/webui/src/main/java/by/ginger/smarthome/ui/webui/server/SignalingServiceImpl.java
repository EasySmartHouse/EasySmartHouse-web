/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.server;

import by.ginger.smarthome.provider.device.alarm.SignalingElement;
import by.ginger.smarthome.provider.device.alarm.SignalingModule;
import by.ginger.smarthome.provider.device.exception.DeviceException;
import by.ginger.smarthome.ui.webui.client.rpc.SignalingService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author mirash
 */
public class SignalingServiceImpl extends ServerBaseServlet implements SignalingService {

    private final Logger log = Logger.getLogger(SignalingServiceImpl.class.getName());
    @Autowired
    SignalingModule signalingModule;

    @Override
    public List<SignalingElement> getSignalingElements() {
        return signalingModule.getDevices();
    }

    @Override
    public void setEnabled(String address, boolean enabled) {
        try {
            signalingModule.setEnabled(address, enabled);
        } catch (DeviceException ex) {
            log.error("Cannot change the state of: [" + address + "]", ex);
        }
    }
}
