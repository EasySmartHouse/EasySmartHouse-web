/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.rpc.mock;

import by.ginger.smarthome.provider.device.actuator.Actuator;
import by.ginger.smarthome.provider.device.actuator.SimpleSwitch;
import by.ginger.smarthome.ui.webui.client.rpc.ActuatorsServiceAsync;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author mirash
 */
public class ActuatorsAsyncMock implements ActuatorsServiceAsync {

    private static final int TIMER_DELAY = 1000;
    private List<Actuator> actuators = new LinkedList<Actuator>();

    private void init() {
        SimpleSwitch light1 = new SimpleSwitch();
        light1.setAddress("9800000020EC3105");
        light1.setDescription("Свет в гостинной");
        light1.setLabel("Lamp 1");

        actuators.add(light1);
    }

    public ActuatorsAsyncMock() {
        init();
    }

    @Override
    public void getActuators(final AsyncCallback<List<Actuator>> asyncCallback) {
        final Timer timer = new Timer() {
            @Override
            public void run() {
                asyncCallback.onSuccess(actuators);
            }
        };
        timer.schedule(TIMER_DELAY);
    }

    @Override
    public void changeState(String address, Boolean state, AsyncCallback<Void> callback) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changeState(String address, Double state, AsyncCallback<Void> callback) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
