/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.view;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwtmvc.client.Controller;
import com.googlecode.gwtmvc.client.ModelForView;
import com.googlecode.gwtmvc.client.ModelProxy;

/**
 *
 * @author mirash
 */
public abstract class GridTabView<M extends ModelProxy> extends TabView {

    protected Grid grid;

    public GridTabView(String id, Controller controller, ModelProxy[] models) {
        super(id, controller, models);
    }

    @Override
    public Widget createWidget() {
        Widget widget = super.createWidget();

        grid = new Grid();
        super.addWidget(grid);

        return widget;
    }

    abstract void delegateModelChange(M m);

    @Override
    public void onModelChange(ModelForView model) {
        super.onModelChange(model);

        this.delegateModelChange((M) model);
    }

    @Override
    public void onRender() {
        grid.setStyleName("tab-grid");
        grid.addStyleName("table-striped");
        grid.setVisible(true);
    }
}
