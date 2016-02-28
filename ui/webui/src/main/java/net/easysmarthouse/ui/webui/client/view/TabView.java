/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.view;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwtmvc.client.Controller;
import com.googlecode.gwtmvc.client.ModelForView;
import com.googlecode.gwtmvc.client.ModelProxy;
import com.googlecode.gwtmvc.client.View;

/**
 *
 * @author mirash
 */
public abstract class TabView extends View {

    protected FlowPanel panel;

    public TabView(String id, Controller controller, ModelProxy[] models) {
        super(id, controller, models);
    }

    @Override
    public Widget createWidget() {
        panel = new FlowPanel();
        return panel;
    }

    @Override
    public void onModelChange(ModelForView model) {
        this.ensureWidget();
    }

    public void addWidget(Widget widget) {
        panel.add(widget);
    }
}
