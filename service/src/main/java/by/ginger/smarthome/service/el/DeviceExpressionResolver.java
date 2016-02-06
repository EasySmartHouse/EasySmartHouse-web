/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.service.el;

import by.ginger.smarthome.el.context.DeviceContext;
import by.ginger.smarthome.el.expression.Expression;
import by.ginger.smarthome.el.parser.EvaluationTemplate;
import by.ginger.smarthome.el.parser.ExpressionParser;
import by.ginger.smarthome.el.parser.SimpleExpressionParser;
import by.ginger.smarthome.el.parser.StandardEvaluationTemplate;
import by.ginger.smarthome.el.trigger.ExpressionTriggerCondition;
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
