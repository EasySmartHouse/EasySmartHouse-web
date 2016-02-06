/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.controller;

import by.ginger.smarthome.ui.webui.client.PanelManager.PanelKey;
import by.ginger.smarthome.ui.webui.client.model.CamerasModel;
import by.ginger.smarthome.ui.webui.client.view.CamerasView;
import by.ginger.smarthome.ui.webui.client.view.TabView;
import by.ginger.smarthome.ui.webui.client.view.ViewId;
import com.googlecode.gwtmvc.client.ModelProxy;

/**
 *
 * @author rusakovich
 */
public class CamerasController extends BaseTabController {

    public CamerasController(PanelKey panelKey) {
        super(TabAction.values(), panelKey);
    }

    @Override
    public ModelProxy createModel() {
        return new CamerasModel();
    }

    @Override
    public TabView createView(String viewId, ModelProxy[] model) {
        return new CamerasView(viewId, this, model);
    }

    @Override
    public String getViewId() {
        return ViewId.CAMERAS_VIEW_ID;
    }

}
