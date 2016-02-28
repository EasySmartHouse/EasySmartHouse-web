/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.controller;

import net.easysmarthouse.ui.webui.client.PanelManager.PanelKey;
import net.easysmarthouse.ui.webui.client.model.ActuatorsModel;
import net.easysmarthouse.ui.webui.client.model.ChangeableModel;
import net.easysmarthouse.ui.webui.client.view.ActuatorsView;
import net.easysmarthouse.ui.webui.client.view.TabView;
import net.easysmarthouse.ui.webui.client.view.ViewId;
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
