/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.view.widget;

/**
 *
 * @author rusakovich
 */
public enum SliderOption {

    DISABLED("disabled"),
    ANIMATE("animate"),
    MAX("max"),
    MIN("min"),
    ORIENTATION("orientation"),
    RANGE("range"),
    STEP("step"),
    VALUE("value"),
    VALUES("values");

    private final String optionName;

    private SliderOption(String optionName) {
        this.optionName = optionName;
    }

    @Override
    public String toString() {
        return this.optionName;
    }

}
