/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.el.context;

import net.easysmarthouse.el.expression.Expression;
import net.easysmarthouse.network.NetworkManager;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mirash
 */
public class SimpleEvaluationContext implements EvaluationContext {

    private Map<Expression, Object> evaluationResults = new HashMap<>();
    private final NetworkManager networkManager;

    public SimpleEvaluationContext(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    @Override
    public Object getEvaluationResult(Expression expression) {
        return evaluationResults.get(expression);
    }

    @Override
    public void addEvaluationResult(Expression expression, Object value) {
        evaluationResults.put(expression, value);
    }

    @Override
    public NetworkManager getNetworkManager() {
        return networkManager;
    }
}
