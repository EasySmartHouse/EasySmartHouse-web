/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.el.context;

import by.ginger.smarthome.el.expression.Expression;
import by.ginger.smarthome.network.NetworkManagerAccessable;

/**
 *
 * @author mirash
 */
public interface EvaluationContext extends NetworkManagerAccessable{

    public Object getEvaluationResult(Expression expression);

    public void addEvaluationResult(Expression expression, Object result);
      
}
