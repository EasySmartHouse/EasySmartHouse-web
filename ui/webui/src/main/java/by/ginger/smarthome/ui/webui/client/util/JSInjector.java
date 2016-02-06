/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.util;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadElement;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.dom.client.Element;

/**
 *
 * @author rusakovich
 */
public class JSInjector {

    private static HeadElement head;
    
    private JSInjector(){
    }

    public static void inject(String javascript) {
        HeadElement head = getHead();
        ScriptElement element = createScriptElement();
        element.setText(javascript);
        head.appendChild(element);
    }

    private static ScriptElement createScriptElement() {
        ScriptElement script = Document.get().createScriptElement();
        script.setAttribute("language", "javascript");
        return script;
    }

    private static HeadElement getHead() {
        if (head == null) {
            Element element = Document.get()
                    .getElementsByTagName("head")
                    .getItem(0);

            assert element != null : "HTML Head element required";
            HeadElement head = HeadElement.as(element);
            JSInjector.head = head;
        }
        return JSInjector.head;
    }

}
