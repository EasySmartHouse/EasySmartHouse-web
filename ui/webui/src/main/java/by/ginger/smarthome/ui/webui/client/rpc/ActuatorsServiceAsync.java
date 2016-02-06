/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.rpc;

import by.ginger.smarthome.provider.device.actuator.Actuator;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author mirash
 */
public interface ActuatorsServiceAsync {

    public void getActuators(AsyncCallback<List<Actuator>> asyncCallback);

    public void changeState(String address, Boolean state, AsyncCallback<Void> callback);

    public void changeState(String address, Double state, AsyncCallback<Void> callback);
}
