/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.el.parser;

import by.ginger.smarthome.el.expression.Expression;


/**
 *
 * @author mirash
 */
public interface ExpressionParser {

    public Expression parse(String expressionStr) throws Exception;
}
