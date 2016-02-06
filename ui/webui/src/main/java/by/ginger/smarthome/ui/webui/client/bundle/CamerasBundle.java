/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.bundle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.resources.client.TextResource;

/**
 *
 * @author mirash
 */
public interface CamerasBundle extends ClientBundle {

    public static final CamerasBundle INSTANCE = GWT.create(CamerasBundle.class);

    @Source("webcam-ws.js")
    TextResource webcamJs();

    @Source("webcam.fragment")
    TextResource webcamFragment();

    @NotStrict
    @Source("Webcam.css")
    CssResource webcamStyle();
}
