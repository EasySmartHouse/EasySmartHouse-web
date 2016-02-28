/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.view.widget;

import com.google.gwt.user.client.Event;

/**
 *
 * @author rusakovich
 */
public class SliderEvent {

    private double[] values;
    private boolean hasOriginalEvent = true;
    private Slider source;
    private Event event;

    public SliderEvent(Event event, Slider source, double[] values) {
        this(event, source, values, true);
    }

    public SliderEvent(Event event, Slider source, double[] values, boolean hasOriginalEvent) {
        this.source = source;
        this.event = event;
        this.values = values;
        this.hasOriginalEvent = hasOriginalEvent;
    }

    public Event getEvent() {
        return event;
    }

    public Slider getSource() {
        return source;
    }

    public double[] getValues() {
        return values;
    }

    public boolean hasOriginalEvent() {
        return hasOriginalEvent;
    }

}
