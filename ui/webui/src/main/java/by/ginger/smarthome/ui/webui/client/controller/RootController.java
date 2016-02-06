/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.controller;

import by.ginger.smarthome.ui.webui.client.PanelManager;
import java.util.HashMap;
import java.util.Map;
import by.ginger.smarthome.ui.webui.client.PanelManager.PanelKey;
import by.ginger.smarthome.ui.webui.client.controller.BaseTabController.TabAction;
import by.ginger.smarthome.ui.webui.client.history.HistoryChangeListener;
import by.ginger.smarthome.ui.webui.client.history.HistoryWorker;
import by.ginger.smarthome.ui.webui.client.view.RootView;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gwtmvc.client.BrowserEvent;
import com.googlecode.gwtmvc.client.Controller;
import com.googlecode.gwtmvc.client.IView;
import com.googlecode.gwtmvc.client.MvcEvent;

/**
 *
 * @author rusakovich
 */
public class RootController extends Controller implements HistoryChangeListener {

    private static final String ROOT_TOKEN = "home";
    private HistoryWorker historyWorker;
    private RootView rootView;
    private Map<PanelKey, BaseTabController> controllers;

    public RootController() {
        super(RootControllerAction.values());
    }

    @Override
    public void fireHistoryChange(String token) {
        selectTab(token);
    }

    public static enum RootControllerAction {

        SHOW_WELCOME,
        TAB_SELECTED
    }

    @Override
    public void init() {
        controllers = new HashMap<PanelKey, BaseTabController>();

        final MonitoringController monitoringController = new MonitoringController(PanelKey.MONITORING);
        controllers.put(PanelKey.MONITORING, monitoringController);

        final ActuatorsController actuatorsController = new ActuatorsController(PanelKey.SWITCHING);
        controllers.put(PanelKey.SWITCHING, actuatorsController);

        final SignalingController signalingController = new SignalingController(PanelKey.SIGNALING);
        controllers.put(PanelKey.SIGNALING, signalingController);

        final CamerasController camerasController = new CamerasController(PanelKey.CAMERAS);
        controllers.put(PanelKey.CAMERAS, camerasController);

        final TriggerController triggerController = new TriggerController(PanelKey.TRIGGER);
        controllers.put(PanelKey.TRIGGER, triggerController);

        rootView = new RootView(this);

        historyWorker = HistoryWorker.createInstance(ROOT_TOKEN);
        historyWorker.addHistoryListener(this);
        historyWorker.addHistoryListener(rootView);
    }

    @Override
    public void showHomeView() {
        this.renderView(rootView);
        selectTab(History.getToken());
    }

    protected void selectTab(String key) {
        PanelKey panelKey = PanelManager.getPanelKeyByToken(key);

        if (panelKey != null) {
            selectTab(panelKey);
        }
    }

    protected void selectTab(PanelKey key) {
        final BaseTabController tabController = controllers.get(key);

        if (tabController != null) {
            tabController.call(new MvcEvent<Void>(TabAction.SHOW));
        }

        for (BaseTabController controller : controllers.values()) {
            if (controller != tabController) {
                controller.call(new MvcEvent<Void>(TabAction.HIDE));
            }
        }

        HistoryWorker.setHistoryToken(key.getToken());
    }

    @Override
    protected boolean handleBrowserEvent(BrowserEvent browserEvent) {
        String action = browserEvent.getAction();

        showHomeView();

        if (ROOT_TOKEN.equalsIgnoreCase(action) || action.isEmpty()) {
            return true;
        }

        return (PanelManager.getPanelKeyByToken(action) != null);
    }

    @Override
    protected void handleEvent(MvcEvent event) {
        final Enum actionEnum = event.getAction();
        if (!(actionEnum instanceof RootControllerAction)) {
            return;
        }

        RootControllerAction action = (RootControllerAction) actionEnum;
        switch (action) {
            case TAB_SELECTED:
                selectTab((PanelKey) event.getValue());
                break;
        }

    }

    @Override
    protected void renderView(IView iview) {
        if (iview instanceof RootView) {
            final RootView view = (RootView) iview;
            RootPanel.get("content").add(view);
            view.render();
            view.fireHistoryChange(History.getToken());
        }
    }
}
