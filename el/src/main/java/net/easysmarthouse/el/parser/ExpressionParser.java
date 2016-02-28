/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.el.parser;

import net.easysmarthouse.el.expression.Expression;


/**
 *
 * @author mirash
 */
public interface ExpressionParser {

    public Expression parse(String expressionStr) throws Exception;
}
