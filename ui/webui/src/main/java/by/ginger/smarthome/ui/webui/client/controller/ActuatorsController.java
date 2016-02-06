/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.controller;

import by.ginger.smarthome.ui.webui.client.PanelManager.PanelKey;
import by.ginger.smarthome.ui.webui.client.model.ActuatorsModel;
import by.ginger.smarthome.ui.webui.client.model.ChangeableModel;
import by.ginger.smarthome.ui.webui.client.view.ActuatorsView;
import by.ginger.smarthome.ui.webui.client.view.TabView;
import by.ginger.smarthome.ui.webui.client.view.ViewId;
import com.googlecode.gwtmvc.client.ModelProxy;

/**
 *
 * @author mirash
 */
public class ActuatorsController extends BaseTabController {

    public ActuatorsController(PanelKey panelKey) {
        super(TabAction.values(), panelKey);
    }

    @Override
    public ChangeableModel createModel() {
        return new ActuatorsModel();
    }

    @Override
    public TabView createView(String viewId, ModelProxy[] model) {
        return new ActuatorsView(viewId, this, model);
    }

    @Override
    public String getViewId() {
        return ViewId.ACTUATORS_VIEW_ID;
    }
}
