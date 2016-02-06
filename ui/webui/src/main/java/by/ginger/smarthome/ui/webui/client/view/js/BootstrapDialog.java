/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.view.js;

import com.google.gwt.core.client.JavaScriptObject;

/**
 *
 * @author rusakovich
 */
public class BootstrapDialog extends JavaScriptObject {

    protected BootstrapDialog() {
    }

    public static native BootstrapDialog get() /*-{
     return $wnd.BootstrapDialog; 
     }-*/;

    public final native void alert(String message)/*-{
     this.alert(message);
     }-*/;

    private final native void show(JavaScriptObject jsonObject)/*-{
     this.show(jsonObject);
     }-*/;

    public final static native JavaScriptObject createCloseFunction() /*-{
     return function(dialogItself){dialogItself.close();};
     }-*/;

    public final void show(BootstrapDialogParams param) {
        show(param.getJSObject());
    }

}
