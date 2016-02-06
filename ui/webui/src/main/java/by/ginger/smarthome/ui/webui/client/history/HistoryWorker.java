/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.history;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mirash
 */
public class HistoryWorker implements ValueChangeHandler<String>, HistoryChangeObservable {
    
    private List<HistoryChangeListener> historyChangeListeners = new ArrayList<HistoryChangeListener>();

    private void initHistorySupport(String homeItem) {
        String initToken = History.getToken();

        if (initToken.length() == 0) {
            History.newItem(homeItem);
        }

        History.addValueChangeHandler(this);
        History.fireCurrentHistoryState();
    }

    private HistoryWorker(String homeItem) {
        initHistorySupport(homeItem);
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        String historyToken = event.getValue();

        for (HistoryChangeListener historyListener : historyChangeListeners) {
            historyListener.fireHistoryChange(historyToken);
        }
    }

    public static HistoryWorker createInstance(String homeItem) {
        return new HistoryWorker(homeItem);
    }

    public static void setHistoryToken(String token) {
        History.newItem(token);
    }

    @Override
    public void addHistoryListener(HistoryChangeListener instance) {
        historyChangeListeners.add(instance);
    }

    @Override
    public void removeHistoryListener(HistoryChangeListener instance) {
        historyChangeListeners.remove(instance);
    }
}
