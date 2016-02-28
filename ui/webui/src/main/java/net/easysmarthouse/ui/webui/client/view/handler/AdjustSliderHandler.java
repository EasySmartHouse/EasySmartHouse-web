/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.view.handler;

import net.easysmarthouse.ui.webui.client.rpc.BaseCallback;
import net.easysmarthouse.ui.webui.client.view.widget.SliderEvent;
import net.easysmarthouse.ui.webui.client.view.widget.SliderListener;
import net.easysmarthouse.ui.webui.client.view.widget.Slider;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author rusakovich
 */
public class AdjustSliderHandler extends BaseHandler implements SliderListener {

    private final Slider source;
    private final String deviceAddress;

    public AdjustSliderHandler(Slider source, String deviceAddress) {
        this.source = source;
        this.deviceAddress = deviceAddress;
    }

    @Override
    public void onStart(SliderEvent event) {
    }

    @Override
    public boolean onSlide(SliderEvent event) {
        return true;
    }

    @Override
    public void onChange(SliderEvent event) {
    }

    @Override
    public void onStop(SliderEvent event) {
        if (event.getSource() == source) {
            double value = event.getValues()[0];

            AsyncCallback<Void> callback = new BaseCallback<Void>() {
                @Override
                public void onSuccess(Void result) {
                }
            };

            this.serviceLocator.getActuators()
                    .changeState(deviceAddress, value, callback);
        }
    }

}
