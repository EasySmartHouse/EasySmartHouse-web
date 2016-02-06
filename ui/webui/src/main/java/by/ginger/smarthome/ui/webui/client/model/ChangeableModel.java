/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.model;

import by.ginger.smarthome.ui.webui.client.rpc.ServiceLocator;
import com.google.gwt.user.client.Timer;
import com.googlecode.gwtmvc.client.ModelProxy;

/**
 *
 * @author mirash
 */
public abstract class ChangeableModel<T> extends ModelProxy<T> {

    private static final int UPDATE_INTERVAL = 500;
    
    protected final Timer timer;
    protected volatile boolean update;
    
    protected ServiceLocator serviceLocator;

    public ChangeableModel() {
        timer = new Timer() {
            @Override
            public void run() {
                updateModel();
            }
        };
        update = true;
    }

    protected final void delegateOnSuccess(T result) {
        value = result;

        onChange();

        if (update) {
            timer.schedule(UPDATE_INTERVAL);
        }
    }

    abstract void updateModel();

    @Override
    protected void init() {
        serviceLocator = ServiceLocator.instance();
    }

    public void startUpdate() {
        update = true;
        timer.schedule(UPDATE_INTERVAL);
    }

    public void stopUpdate() {
        timer.cancel();
        update = false;
    }
}
