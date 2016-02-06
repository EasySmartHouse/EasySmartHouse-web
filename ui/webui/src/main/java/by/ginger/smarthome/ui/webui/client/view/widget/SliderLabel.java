/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.view.widget;

import by.ginger.smarthome.ui.webui.client.util.MessageFormat;
import com.google.gwt.user.client.ui.Label;

/**
 *
 * @author rusakovich
 */
public class SliderLabel extends Label implements SliderListener {

    private static final int DECIMAL_COUNT = 2;

    private final Slider source;

    public SliderLabel(Slider source) {
        this.source = source;
    }

    public SliderLabel(Slider source, double defaultValue) {
        super(MessageFormat.getFormatted(defaultValue, DECIMAL_COUNT));
        this.source = source;
    }

    @Override
    public void onStart(SliderEvent event) {
    }

    @Override
    public boolean onSlide(SliderEvent event) {
        if (event.getSource() == source) {
            double value = event.getValues()[0];
            this.setText(MessageFormat.getFormatted(value, DECIMAL_COUNT));
        }
        return true;
    }

    @Override
    public void onChange(SliderEvent event) {
    }

    @Override
    public void onStop(SliderEvent event) {
    }

}
