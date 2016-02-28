/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.el.expression.actuator;

import net.easysmarthouse.el.context.EvaluationContext;
import net.easysmarthouse.el.expression.ValueAware;

/**
 *
 * @author mirash
 */
public abstract class AdjustableValueExpression extends ActuatorExpression<Double> implements ValueAware<Double>{

    protected double adjustableValue;

    public AdjustableValueExpression(EvaluationContext context) {
        super(context);
    }

    public double getAdjustableValue() {
        return adjustableValue;
    }

    /**
     *
     * @param adjustableValue
     */
    @Override
    public void setValue(Double adjustableValue) {
        this.adjustableValue = adjustableValue;
    }
}
