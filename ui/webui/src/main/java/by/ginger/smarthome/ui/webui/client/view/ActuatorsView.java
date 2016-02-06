/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.view;

import by.ginger.smarthome.provider.device.actuator.Actuator;
import static by.ginger.smarthome.provider.device.actuator.ActuatorType.adjustableActuator;
import static by.ginger.smarthome.provider.device.actuator.ActuatorType.switchActuator;
import by.ginger.smarthome.provider.device.actuator.AdjustableActuator;
import by.ginger.smarthome.provider.device.actuator.SwitchActuator;
import by.ginger.smarthome.provider.device.exception.DeviceException;
import by.ginger.smarthome.ui.i18n.Actuators;
import by.ginger.smarthome.ui.webui.client.messages.MessagesHolder;
import by.ginger.smarthome.ui.webui.client.model.ActuatorsModel;
import by.ginger.smarthome.ui.webui.client.view.handler.AdjustSliderHandler;
import by.ginger.smarthome.ui.webui.client.view.handler.ToggleSwitchHandler;
import by.ginger.smarthome.ui.webui.client.view.widget.Slider;
import by.ginger.smarthome.ui.webui.client.view.widget.SliderLabel;
import by.ginger.smarthome.ui.webui.client.view.widget.SliderOption;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwtmvc.client.Controller;
import com.googlecode.gwtmvc.client.ModelProxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author mirash
 */
public class ActuatorsView extends GridTabView<ActuatorsModel> {

    private Actuators actuatorsMessages = MessagesHolder.getInstance().getActuatorsMessages();

    private Map<String, Widget> sliderWidgets = new HashMap<String, Widget>();
    private Map<String, Widget> sliderLabelWidgets = new HashMap<String, Widget>();

    public ActuatorsView(String id, Controller controller, ModelProxy[] models) {
        super(id, controller, models);
    }

    @Override
    public Widget createWidget() {
        Widget widget = super.createWidget();
        grid.resize(1, 5);
        return widget;
    }

    @Override
    public void onRender() {
        super.onRender();

        grid.setHTML(0, 0, actuatorsMessages.tableTitleColumnNumber());
        grid.setHTML(0, 1, actuatorsMessages.tableTitleColumnName());
        grid.setHTML(0, 2, actuatorsMessages.tableTitleColumnDescription());
        grid.setHTML(0, 3, actuatorsMessages.tableTitleColumnStatus());
        grid.setHTML(0, 4, actuatorsMessages.tableTitleColumnAction());
    }

    @Override
    void delegateModelChange(ActuatorsModel actuatorsModel) {

        final List<Actuator> actuators = actuatorsModel.getValue();

        int row = 1;
        grid.resizeRows(actuators.size() + 1);

        for (Actuator actuator : actuators) {

            grid.setHTML(row, 0, Integer.toString(row));
            grid.setHTML(row, 1, actuator.getLabel());
            grid.setHTML(row, 2, actuator.getDescription());

            String address = actuator.getAddress();
            Widget widget = null;

            switch (actuator.getActuatorType()) {
                case switchActuator:
                    final SwitchActuator switchActuator = (SwitchActuator) actuator;

                    String state;
                    try {
                        state = (switchActuator.getState())
                                ? actuatorsMessages.tableLabelSwitchStateOn()
                                : actuatorsMessages.tableLabelSwitchStateOff();

                    } catch (DeviceException ex) {
                        state = ex.getMessage();
                    }
                    grid.setHTML(row, 3, state);

                    Button changeSwitchStateButton = new Button();
                    changeSwitchStateButton.addStyleName("btn");
                    changeSwitchStateButton.addStyleName("btn-primary");
                    changeSwitchStateButton.addStyleName("btn-xs");
                    
                    changeSwitchStateButton.setText(actuatorsMessages.tableButtonTextSwitchStateChange());
                    changeSwitchStateButton.addClickHandler(
                            new ToggleSwitchHandler(switchActuator));

                    widget = changeSwitchStateButton;

                    break;
                case adjustableActuator:
                    final AdjustableActuator adjustableActuator = (AdjustableActuator) actuator;

                    Label label = null;
                    if (!sliderWidgets.containsKey(address)) {

                        Double currrentState = null;
                        try {
                            currrentState = adjustableActuator.getState();
                        } catch (DeviceException ex) {
                            currrentState = adjustableActuator.getDefaultValue();
                        }

                        JSONObject options = Slider.getOptions(adjustableActuator.getMinValue(), adjustableActuator.getMaxValue(),
                                new double[]{currrentState});
                        options.put(SliderOption.STEP.toString(), new JSONNumber(adjustableActuator.getChangeStep()));

                        Slider actuatorChangeSlider = new Slider(adjustableActuator.getAddress() + "_" + "slider", options);
                        widget = actuatorChangeSlider;

                        SliderLabel sliderLabel = new SliderLabel(actuatorChangeSlider, currrentState);
                        actuatorChangeSlider.addListener(sliderLabel);
                        label = sliderLabel;

                        AdjustSliderHandler sliderHandler = new AdjustSliderHandler(actuatorChangeSlider, address);
                        actuatorChangeSlider.addListener(sliderHandler);

                        sliderLabelWidgets.put(address, sliderLabel);
                        sliderWidgets.put(address, widget);

                    } else {
                        widget = sliderWidgets.get(address);
                        label = (Label) sliderLabelWidgets.get(address);
                    }

                    grid.setWidget(row, 3, label);
                    break;
            }

            grid.setWidget(row, 4, widget);
            row++;
        }

        grid.setVisible(true);
    }
}
