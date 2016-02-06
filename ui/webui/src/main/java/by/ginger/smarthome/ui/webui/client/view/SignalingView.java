/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.view;

import by.ginger.smarthome.provider.device.Device;
import by.ginger.smarthome.provider.device.alarm.SignalingElement;
import by.ginger.smarthome.ui.i18n.Signaling;
import by.ginger.smarthome.ui.webui.client.messages.MessagesHolder;
import by.ginger.smarthome.ui.webui.client.model.SignalingModel;
import by.ginger.smarthome.ui.webui.client.view.handler.SignalingEnableHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwtmvc.client.Controller;
import com.googlecode.gwtmvc.client.ModelProxy;
import java.util.List;

/**
 *
 * @author mirash
 */
public class SignalingView extends GridTabView<SignalingModel> {
    
    private static final String DEVICE_LABEL_DEFAULT = "";
    private final Signaling signalingMessages = MessagesHolder.getInstance().getSignalingMessages();

    public SignalingView(String id, Controller controller, ModelProxy[] models) {
        super(id, controller, models);
    }

    @Override
    public Widget createWidget() {
        Widget widget = super.createWidget();
        grid.resize(1, 4);
        return widget;
    }

    @Override
    public void onRender() {
        super.onRender();

        grid.setHTML(0, 0, signalingMessages.tableTitleColumnNumber());
        grid.setHTML(0, 1, signalingMessages.tableTitleColumnName());
        grid.setHTML(0, 2, signalingMessages.tableTitleColumnStatus());
        grid.setHTML(0, 3, signalingMessages.tableTitleColumnActivation());

    }

    @Override
    void delegateModelChange(SignalingModel signalingModel) {
        List<SignalingElement> signalings = signalingModel.getValue();

        int row = 1;
        grid.resizeRows(signalings.size() + 1);

        for (SignalingElement signalingElement : signalings) {

            grid.setHTML(row, 0, Integer.toString(row));

            String deviceLabel = DEVICE_LABEL_DEFAULT;
            if (signalingElement instanceof Device) {
                Device signalingDevice = (Device) signalingElement;
                deviceLabel = signalingDevice.getLabel();
            }
            grid.setHTML(row, 1, deviceLabel);

            String alarmStatus = (signalingElement.isAlarm())
                    ? signalingMessages.tableLabelSignalingElementOpen()
                    : signalingMessages.tableLabelSignalingElementClosed();
            grid.setHTML(row, 2, alarmStatus);

            CheckBox enableCheckBox = new CheckBox();
            enableCheckBox.setValue(signalingElement.isEnabled());
            enableCheckBox.addClickHandler(new SignalingEnableHandler(signalingElement));
            grid.setWidget(row, 3, enableCheckBox);

            row++;
        }

        grid.setVisible(true);
    }
}
