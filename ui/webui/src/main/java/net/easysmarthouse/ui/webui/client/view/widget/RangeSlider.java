/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.view.widget;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;

/**
 *
 * @author rusakovich
 */
public class RangeSlider extends Slider {

    public RangeSlider(String id, int min, int max, int defaultMin, int defaultMax) {
        super(id, getOptions(min, max, defaultMin, defaultMax));
    }

    public static JSONObject getOptions(int min, int max, int defaultMin, int defaultMax) {
        JSONObject options = Slider.getOptions(min, max, new double[]{defaultMin, defaultMax});
        options.put(SliderOption.RANGE.toString(), JSONBoolean.getInstance(true));
        return options;
    }

    public int getValueMin() {
        return getValueAtIndex(0);
    }

    public int getValueMax() {
        return getValueAtIndex(1);
    }

    public void setValues(int min, int max) {
        setValues(new double[]{min, max});
    }

}
