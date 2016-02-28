/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.controller;

import net.easysmarthouse.ui.webui.client.PanelManager.PanelKey;
import net.easysmarthouse.ui.webui.client.model.ChangeableModel;
import net.easysmarthouse.ui.webui.client.view.MonitoringView;
import net.easysmarthouse.ui.webui.client.view.ViewId;
import net.easysmarthouse.ui.webui.client.model.MonitoringModel;
import net.easysmarthouse.ui.webui.client.view.TabView;
import com.googlecode.gwtmvc.client.ModelProxy;

/**
 *
 * @author rusakovich
 */
public class MonitoringController extends BaseTabController {

    public MonitoringController(PanelKey panelKey) {
        super(TabAction.values(), panelKey);
    }

    @Override
    public ChangeableModel createModel() {
        return new MonitoringModel();
    }

    @Override
    public TabView createView(String viewId, ModelProxy[] model) {
        return new MonitoringView(viewId, this, model);
    }

    @Override
    public String getViewId() {
        return ViewId.MONITORING_VIEW_ID;
    }
}
