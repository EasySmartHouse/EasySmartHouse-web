/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.service.el;

import net.easysmarthouse.el.context.DeviceContext;
import net.easysmarthouse.el.expression.Expression;
import net.easysmarthouse.el.parser.EvaluationTemplate;
import net.easysmarthouse.el.parser.ExpressionParser;
import net.easysmarthouse.el.parser.SimpleExpressionParser;
import net.easysmarthouse.el.parser.StandardEvaluationTemplate;
import net.easysmarthouse.el.trigger.ExpressionTriggerCondition;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanExpressionContext;
import org.springframework.beans.factory.config.BeanExpressionResolver;

/**
 *
 * @author rusakovich
 */
public class DeviceExpressionResolver implements BeanExpressionResolver {

    private EvaluationTemplate evaluationTemplate;
    private BeanExpressionResolver delegated;
    private ExpressionParser parser;

    private ExpressionParser getExpressionParser(DeviceContext deviceContext) {
        SimpleExpressionParser simpleParser = new SimpleExpressionParser(deviceContext);
        simpleParser.setEvaluationTemplate(evaluationTemplate);
        return simpleParser;
    }

    public DeviceExpressionResolver(DeviceContext deviceContext) {
        this.evaluationTemplate = new StandardEvaluationTemplate();
        this.parser = getExpressionParser(deviceContext);
    }

    @Override
    public Object evaluate(String value, BeanExpressionContext expressionContext) throws BeansException {
        if (value.startsWith(evaluationTemplate.getEvaluationStart()) && value.endsWith(evaluationTemplate.getEvaluationEnd())) {
            try {
                return parser.parse(value);
            } catch (Exception ex) {
                throw new BeansException(ex.getMessage()) {
                };
            }
        } else {
            return delegated.evaluate(value, expressionContext);
        }
    }

    public void setDelegated(BeanExpressionResolver delegated) {
        this.delegated = delegated;
    }
}
