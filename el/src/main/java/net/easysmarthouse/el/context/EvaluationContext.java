/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.el.context;

import net.easysmarthouse.el.expression.Expression;
import net.easysmarthouse.network.NetworkManagerAccessable;

/**
 *
 * @author mirash
 */
public interface EvaluationContext extends NetworkManagerAccessable{

    public Object getEvaluationResult(Expression expression);

    public void addEvaluationResult(Expression expression, Object result);
      
}
