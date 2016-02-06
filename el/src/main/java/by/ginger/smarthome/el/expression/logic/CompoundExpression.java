/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.el.expression.logic;

import by.ginger.smarthome.el.context.EvaluationContext;
import by.ginger.smarthome.el.expression.AbstractExpression;
import by.ginger.smarthome.el.expression.ExpressionAware;
import by.ginger.smarthome.el.expression.Expression;

/**
 *
 * @author mirash
 */
public abstract class CompoundExpression extends LogicExpression implements ExpressionAware {

    protected AbstractExpression exprOne;
    protected AbstractExpression exprAnother;

    public CompoundExpression(EvaluationContext context) {
        super(context);
    }

    public Expression getExprOne() {
        return exprOne;
    }

    @Override
    public void setExprOne(Expression exprOne) {
        this.exprOne = (AbstractExpression) exprOne;
    }

    public Expression getExprAnother() {
        return exprAnother;
    }

    @Override
    public void setExprAnother(Expression exprAnother) {
        this.exprAnother = (AbstractExpression) exprAnother;
    }
}
