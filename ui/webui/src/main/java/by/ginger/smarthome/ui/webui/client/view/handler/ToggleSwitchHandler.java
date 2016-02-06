/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.view.handler;

import by.ginger.smarthome.provider.device.actuator.SwitchActuator;
import by.ginger.smarthome.provider.device.exception.DeviceException;
import by.ginger.smarthome.ui.webui.client.rpc.BaseCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author mirash
 */
public class ToggleSwitchHandler extends BaseHandler implements ClickHandler {

    private final SwitchActuator actuator;

    public ToggleSwitchHandler(SwitchActuator actuator) {
        this.actuator = actuator;
    }

    @Override
    public void onClick(ClickEvent event) {
        try {
            boolean currentState = actuator.getState();
            final String address = actuator.getAddress();

            AsyncCallback<Void> callback = new BaseCallback<Void>() {
                @Override
                public void onSuccess(Void result) {
                }
            };

            this.serviceLocator.getActuators()
                    .changeState(address, !currentState, callback);

        } catch (DeviceException ex) {
            ex.printStackTrace();
        }
    }
}
