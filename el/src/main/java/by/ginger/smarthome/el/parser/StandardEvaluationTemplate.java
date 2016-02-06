/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.el.parser;

/**
 *
 * @author mirash
 */
public class StandardEvaluationTemplate implements EvaluationTemplate {

    @Override
    public String getEvaluationStart() {
        return "$${";
    }

    @Override
    public String getEvaluationEnd() {
        return "}";
    }
}
