/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.view.js;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

/**
 *
 * @author rusakovich
 */
public class BootstrapDialogParams {

    private final JSONObject param;

    private BootstrapDialogParams() {
        this.param = new JSONObject();
    }

    public class Button {

        private final JSONObject button;

        public Button(String id) {
            this.button = new JSONObject();
            this.button.put("id", new JSONString(id));
        }

        public void setIcon(String icon) {
            this.button.put("icon", new JSONString(icon));
        }

        public void setLabel(String label) {
            this.button.put("label", new JSONString(label));
        }

        public void setCssClass(String cssClass) {
            this.button.put("cssClass", new JSONString(cssClass));
        }

        public void setAutospin(boolean autospin) {
            this.button.put("autospin", JSONBoolean.getInstance(autospin));
        }

        public void setAction(JavaScriptObject function) {
            this.button.put("action", new JSONObject(function));
        }

    }

    public void setTitle(String title) {
        param.put("title", new JSONString(title));
    }

    public void setMessage(String message) {
        param.put("message", new JSONString(message));
    }

    public void setButtons(Button[] buttons) {
        JSONArray buttonsArray = new JSONArray();
        for (int i = 0; i < buttons.length; i++) {
            Button button = buttons[i];
            buttonsArray.set(i, button.button);
        }
        param.put("buttons", buttonsArray);
    }

    public static BootstrapDialogParams create() {
        return new BootstrapDialogParams();
    }

    @Override
    public String toString() {
        return param.toString();
    }

    public JavaScriptObject getJSObject() {
        return param.getJavaScriptObject();
    }

}
