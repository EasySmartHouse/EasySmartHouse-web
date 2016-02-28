/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.controller;

import net.easysmarthouse.ui.webui.client.PanelManager.PanelKey;
import net.easysmarthouse.ui.webui.client.model.ChangeableModel;
import net.easysmarthouse.ui.webui.client.model.SignalingModel;
import net.easysmarthouse.ui.webui.client.view.SignalingView;
import net.easysmarthouse.ui.webui.client.view.TabView;
import net.easysmarthouse.ui.webui.client.view.ViewId;
import com.googlecode.gwtmvc.client.ModelProxy;

/**
 *
 * @author mirash
 */
public class SignalingController extends BaseTabController {

    public SignalingController(PanelKey panelKey) {
        super(TabAction.values(), panelKey);
    }

    @Override
    public ChangeableModel createModel() {
        return new SignalingModel();
    }

    @Override
    public TabView createView(String viewId, ModelProxy[] model) {
        return new SignalingView(viewId, this, model);
    }

    @Override
    public String getViewId() {
        return ViewId.SIGNALING_VIEW_ID;
    }
}
