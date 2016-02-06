/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.view;

import by.ginger.smarthome.ui.i18n.Cameras;
import by.ginger.smarthome.ui.webui.client.bundle.CamerasBundle;
import by.ginger.smarthome.ui.webui.client.messages.MessagesHolder;
import by.ginger.smarthome.ui.webui.client.model.CamerasModel;
import by.ginger.smarthome.ui.webui.client.util.JSInjector;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwtmvc.client.Controller;
import com.googlecode.gwtmvc.client.ModelProxy;

/**
 *
 * @author rusakovich
 */
public class CamerasView extends GridTabView<CamerasModel> {

    private Cameras camerasMessages = MessagesHolder.getInstance().getCamerasMessages();
    private final CamerasBundle camerasBundle = CamerasBundle.INSTANCE;
    private HTMLPanel camPanel;

    public CamerasView(String id, Controller controller, ModelProxy[] models) {
        super(id, controller, models);
    }

    @Override
    public Widget createWidget() {
        Widget widget = super.createWidget();
        grid.resize(2, 1);

        camPanel = new HTMLPanel(camerasBundle.webcamFragment().getText());

        return widget;
    }

    @Override
    public void onRender() {
        super.onRender();

        grid.setHTML(0, 0, camerasMessages.tableTitleColumnName());
        
        camerasBundle.webcamStyle().ensureInjected();
        JSInjector.inject(camerasBundle.webcamJs().getText());

        grid.setWidget(1, 0, camPanel);

        grid.setVisible(true);
    }

    @Override
    void delegateModelChange(CamerasModel model) {
    }
}
