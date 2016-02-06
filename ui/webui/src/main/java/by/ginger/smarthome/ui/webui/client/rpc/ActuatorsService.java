/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.rpc;

import by.ginger.smarthome.provider.device.actuator.Actuator;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;

/**
 *
 * @author mirash
 */
@RemoteServiceRelativePath("actuators")
public interface ActuatorsService extends RemoteService {

    public List<Actuator> getActuators();

    public void changeState(String address, Boolean state);

    public void changeState(String address, Double state);
}
