/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.view.widget;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author rusakovich
 */
public class Slider extends Widget {

    private JSONObject defaultOptions;
    private List<SliderListener> listeners = new ArrayList<SliderListener>();

    public Slider(String id, double min, double max, double defaultValue) {
        this(id, min, max, new double[]{defaultValue});
    }

    public Slider(String id, double min, double max, double[] defaultValues) {
        this(id, getOptions(min, max, defaultValues));
    }

    public Slider(String id) {
        this(id, null);
    }

    public Slider(String id, JSONObject options) {
        super();
        Element divEle = DOM.createDiv();
        setElement(divEle);
        divEle.setId(id);

        this.defaultOptions = options;
        if (defaultOptions == null) {
            defaultOptions = getOptions(0, 100, new double[]{0});
        }
    }

    public static JSONObject getOptions(double min, double max, double[] defaultValues) {
        JSONObject options = new JSONObject();
        options.put(SliderOption.MIN.toString(), new JSONNumber(min));
        options.put(SliderOption.MAX.toString(), new JSONNumber(max));

        JSONArray vals = doubleArrayToJSONArray(defaultValues);
        options.put(SliderOption.VALUES.toString(), vals);

        return options;
    }

    private static JSONArray doubleArrayToJSONArray(double[] values) {
        JSONArray vals = new JSONArray();
        for (int i = 0, len = values.length; i < len; i++) {
            vals.set(i, new JSONNumber(values[i]));
        }
        return vals;
    }

    @Override
    protected void onLoad() {
        createSliderJS(this, getElement().getId(), defaultOptions.getJavaScriptObject());
        super.onLoad();
    }

    @Override
    protected void onUnload() {
        destroySliderJS(this, getElement().getId());
        super.onUnload();
    }

    public int getMinimum() {
        return getIntOptionJS(getElement().getId(), SliderOption.MIN.toString());
    }

    public void setMinimum(int minimum) {
        setIntOptionJS(getElement().getId(), SliderOption.MIN.toString(), minimum);
    }

    public int getMaximum() {
        return getIntOptionJS(getElement().getId(), SliderOption.MAX.toString());
    }

    public void setMaximum(int maximum) {
        setIntOptionJS(getElement().getId(), SliderOption.MAX.toString(), maximum);
    }

    public int getValue() {
        return getValueAtIndex(0);
    }

    public void setValue(double value) {
       double[] values = {value};
        setValues(values);
    }

    public void setValues(double[] values) {
        JSONArray vals = doubleArrayToJSONArray(values);
        setValuesJS(getElement().getId(), vals.getJavaScriptObject());
    }

    public int getValueAtIndex(int index) {
        return getValueJS(getElement().getId(), index);
    }

    public void setIntOption(SliderOption option, int value) {
        setIntOptionJS(getElement().getId(), option.toString(), value);
    }

    public int getIntOption(SliderOption option) {
        return getIntOptionJS(getElement().getId(), option.toString());
    }

    public void setBooleanOption(SliderOption option, boolean value) {
        setBooleanOptionJS(getElement().getId(), option.toString(), value);
    }

    public boolean getBooleanOption(SliderOption option) {
        return getBooleanOptionJS(getElement().getId(), option.toString());
    }

    public void setStringOption(SliderOption option, String value) {
        setStringOptionJS(getElement().getId(), option.toString(), value);
    }

    public String setStringOption(SliderOption option) {
        return getStringOptionJS(getElement().getId(), option.toString());
    }

    public void addListener(SliderListener listener) {
        listeners.add(listener);
    }

    public void removeListener(SliderListener listener) {
        listeners.remove(listener);
    }

    private void fireOnStartEvent(Event evt, JsArrayNumber values) {
        double[] vals = jsArrayNumberToArray(values);
        SliderEvent e = new SliderEvent(evt, this, vals);

        for (SliderListener listener : listeners) {
            listener.onStart(e);
        }

    }

    private boolean fireOnSlideEvent(Event evt, JsArrayNumber values) {
        double[] vals = jsArrayNumberToArray(values);
        SliderEvent event = new SliderEvent(evt, this, vals);

        for (SliderListener listener : listeners) {
            listener.onStart(event);
        }

        boolean result = true;
        for (SliderListener listener : listeners) {
            if (!listener.onSlide(event)) {
                //if any of the listeners returns false, return false,
                //but let them all do their thing
                result = false;
            }
        }

        return result;
    }

    private void fireOnChangeEvent(Event evt, JsArrayNumber values, boolean hasOriginalEvent) {
        double[] vals = jsArrayNumberToArray(values);
        SliderEvent event = new SliderEvent(evt, this, vals, hasOriginalEvent);

        for (SliderListener listener : listeners) {
            listener.onChange(event);
        }
    }

    private void fireOnStopEvent(Event evt, JsArrayNumber values) {
        double[] vals = jsArrayNumberToArray(values);
        SliderEvent e = new SliderEvent(evt, this, vals);

        for (SliderListener listener : listeners) {
            listener.onStop(e);
        }
    }

    private double[] jsArrayNumberToArray(JsArrayNumber values) {
        int len = values.length();
        double[] vals = new double[len];
        for (int i = 0; i < len; i++) {
            vals[i] = values.get(i);
        }
        return vals;
    }

    private native void setIntOptionJS(String id, String option, int value) /*-{
     $wnd.$("#" + id).slider("option", option, value);
     }-*/;

    private native int getIntOptionJS(String id, String option) /*-{
     return $wnd.$("#" + id).slider("option", option);
     }-*/;

    private native void setBooleanOptionJS(String id, String option, boolean value) /*-{
     $wnd.$("#" + id).slider("option", option, value);
     }-*/;

    private native boolean getBooleanOptionJS(String id, String option) /*-{
     return $wnd.$("#" + id).slider("option", option);
     }-*/;

    private native void setStringOptionJS(String id, String option, String value) /*-{
     $wnd.$("#" + id).slider("option", option, value);
     }-*/;

    private native String getStringOptionJS(String id, String option) /*-{
     return $wnd.$("#" + id).slider("option", option);
     }-*/;

    private native void setValuesJS(String id, JavaScriptObject values) /*-{
     $wnd.$("#" + id).slider("option", "values", values);
     }-*/;

    private native int getValueJS(String id, int index) /*-{
     return $wnd.$("#" + id).slider("values", index);
     }-*/;

    private native void createSliderJS(Slider x, String id, JavaScriptObject options) /*-{
     options.start = function(event, ui) {
     x.@net.easysmarthouse.ui.webui.client.view.widget.Slider::fireOnStartEvent(Lcom/google/gwt/user/client/Event;Lcom/google/gwt/core/client/JsArrayNumber;)(event, ui.values);
     };
     options.slide = function(event, ui) {
     return x.@net.easysmarthouse.ui.webui.client.view.widget.Slider::fireOnSlideEvent(Lcom/google/gwt/user/client/Event;Lcom/google/gwt/core/client/JsArrayNumber;)(event, ui.values);
     };
     options.change = function(event, ui) {
     var has = event.originalEvent ? true : false;
     x.@net.easysmarthouse.ui.webui.client.view.widget.Slider::fireOnChangeEvent(Lcom/google/gwt/user/client/Event;Lcom/google/gwt/core/client/JsArrayNumber;Z)(event, ui.values, has);                
     };
     options.stop = function(event, ui) {
     x.@net.easysmarthouse.ui.webui.client.view.widget.Slider::fireOnStopEvent(Lcom/google/gwt/user/client/Event;Lcom/google/gwt/core/client/JsArrayNumber;)(event, ui.values);
     };
        
     $wnd.$("#" + id).slider(options);
     }-*/;

    private native void destroySliderJS(Slider x, String id) /*-{
     $wnd.$("#" + id).slider("destroy");
     }-*/;
}
