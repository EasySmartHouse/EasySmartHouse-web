/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.helpers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author mirash
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Order {

    public int order();
}
