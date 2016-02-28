/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.controller;

import net.easysmarthouse.ui.webui.client.PanelManager.PanelKey;
import net.easysmarthouse.ui.webui.client.model.TriggerModel;
import net.easysmarthouse.ui.webui.client.view.TabView;
import net.easysmarthouse.ui.webui.client.view.TriggerView;
import net.easysmarthouse.ui.webui.client.view.ViewId;
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
