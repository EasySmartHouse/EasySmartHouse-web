/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.rpc;

import by.ginger.smarthome.provider.device.alarm.SignalingElement;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author mirash
 */
public interface SignalingServiceAsync {

    public void getSignalingElements(AsyncCallback<List<SignalingElement>> asyncCallback);

    public void setEnabled(String address, boolean enabled, AsyncCallback<Void> callback);
}
