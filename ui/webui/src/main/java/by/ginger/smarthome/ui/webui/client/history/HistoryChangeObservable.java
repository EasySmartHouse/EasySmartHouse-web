/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.history;

/**
 *
 * @author mirash
 */
public interface HistoryChangeObservable {

    public void addHistoryListener(HistoryChangeListener instance);

    public void removeHistoryListener(HistoryChangeListener instance);
}
