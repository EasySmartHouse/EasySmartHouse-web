/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.controller;

import by.ginger.smarthome.ui.webui.client.PanelManager.PanelKey;
import by.ginger.smarthome.ui.webui.client.model.TriggerModel;
import by.ginger.smarthome.ui.webui.client.view.TabView;
import by.ginger.smarthome.ui.webui.client.view.TriggerView;
import by.ginger.smarthome.ui.webui.client.view.ViewId;
import com.googlecode.gwtmvc.client.ModelProxy;

/**
 *
 * @author mirash
 */
public class TriggerController extends BaseTabController {

    public TriggerController(PanelKey panelKey) {
        super(TabAction.values(), panelKey);
    }

    @Override
    public ModelProxy createModel() {
        return new TriggerModel();
    }

    @Override
    public TabView createView(String viewId, ModelProxy[] model) {
        return new TriggerView(viewId, this, model);
    }

    @Override
    public String getViewId() {
        return ViewId.TRIGGERS_VIEW_ID;
    }
}
