/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.view;

import by.ginger.smarthome.provider.device.trigger.Trigger;
import by.ginger.smarthome.ui.webui.client.messages.MessagesHolder;
import by.ginger.smarthome.ui.webui.client.model.TriggerModel;
import by.ginger.smarthome.ui.webui.client.view.handler.TriggerActivationHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwtmvc.client.Controller;
import com.googlecode.gwtmvc.client.ModelProxy;
import java.util.List;

/**
 *
 * @author mirash
 */
public class TriggerView extends GridTabView<TriggerModel> {

    private static final String TRIGGER_LABEL_DEFAULT = "";
    private final by.ginger.smarthome.ui.i18n.Trigger triggerMessages = MessagesHolder.getInstance().getTriggersMessages();

    public TriggerView(String id, Controller controller, ModelProxy[] models) {
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

        grid.setHTML(0, 0, triggerMessages.tableTitleColumnNumber());
        grid.setHTML(0, 1, triggerMessages.tableTitleColumnName());
        grid.setHTML(0, 2, triggerMessages.tableTitleColumnStatus());
        grid.setHTML(0, 3, triggerMessages.tableTitleColumnActivation());

    }

    @Override
    void delegateModelChange(TriggerModel triggerModel) {
        List<Trigger> triggers = triggerModel.getValue();

        int row = 1;
        grid.resizeRows(triggers.size() + 1);

        for (Trigger trigger : triggers) {

            grid.setHTML(row, 0, Integer.toString(row));

            String label = (trigger.getName() != null)
                    ? trigger.getName() : TRIGGER_LABEL_DEFAULT;
            grid.setHTML(row, 1, label);

            String activatedStatus = (trigger.isEnabled())
                    ? triggerMessages.tableLabelTriggerActivated()
                    : triggerMessages.tableLabelTriggerDeactivated();
            grid.setHTML(row, 2, activatedStatus);

            CheckBox enableCheckBox = new CheckBox();
            enableCheckBox.setValue(trigger.isEnabled());
            enableCheckBox.addClickHandler(new TriggerActivationHandler(trigger));
            grid.setWidget(row, 3, enableCheckBox);

            row++;
        }

        grid.setVisible(true);
    }
}
