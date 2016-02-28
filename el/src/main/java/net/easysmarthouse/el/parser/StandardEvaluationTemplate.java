/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.el.parser;

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
