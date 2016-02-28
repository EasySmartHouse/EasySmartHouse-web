/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.el.expression.logic;

import net.easysmarthouse.el.context.EvaluationContext;
import net.easysmarthouse.el.expression.AbstractExpression;
import net.easysmarthouse.el.expression.ExpressionAware;
import net.easysmarthouse.el.expression.Expression;

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
