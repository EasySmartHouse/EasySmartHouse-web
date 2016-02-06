/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.controller;

import by.ginger.smarthome.ui.webui.client.PanelManager;
import by.ginger.smarthome.ui.webui.client.PanelManager.PanelKey;
import by.ginger.smarthome.ui.webui.client.model.ChangeableModel;
import by.ginger.smarthome.ui.webui.client.view.TabView;
import com.google.gwt.user.client.ui.Panel;

import com.googlecode.gwtmvc.client.Controller;
import com.googlecode.gwtmvc.client.IView;
import com.googlecode.gwtmvc.client.ModelProxy;
import com.googlecode.gwtmvc.client.MvcEvent;

/**
 *
 * @author rusakovich
 */
public abstract class BaseTabController extends Controller {

    private ModelProxy model;
    private TabView view;

    public static enum TabAction {

        SHOW,
        HIDE
    }
    protected PanelManager.PanelKey panelKey;

    public BaseTabController(PanelKey panelKey) {
        this.panelKey = panelKey;
    }

    public BaseTabController(Enum[] actionEnumValues, PanelKey panelKey) {
        super(actionEnumValues);
        this.panelKey = panelKey;
    }

    public abstract ModelProxy createModel();

    public abstract TabView createView(String viewId, ModelProxy[] model);

    public abstract String getViewId();

    @Override
    protected void init() {
        model = createModel();
        this.initModel(model);

        final ModelProxy[] models = {(ModelProxy) model};
        view = createView(getViewId(), models);

        this.renderView(view);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void handleEvent(MvcEvent event) {
        Object action = event.getAction();

        if (action instanceof TabAction) {
            switch ((TabAction) action) {
                case SHOW:
                    showTab();
                    break;
                case HIDE:
                    hideTab();
                    break;
            }
        }
    }

    @Override
    public void showHomeView() {
    }

    void hideTab() {
        if (model instanceof ChangeableModel) {
            ((ChangeableModel) model).stopUpdate();
        }

    }

    void showTab() {
        if (model instanceof ChangeableModel) {
            ((ChangeableModel) model).startUpdate();
        }
    }

    @Override
    protected void renderView(IView iview) {
        if (iview instanceof TabView) {
            final TabView modelView = (TabView) iview;

            final Panel panel = PanelManager.getPanel(panelKey);
            panel.clear();
            panel.setVisible(true);
            panel.add(modelView);
        }

        iview.render();
    }
}
