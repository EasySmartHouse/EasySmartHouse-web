/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.el.parser;

import by.ginger.smarthome.el.context.DeviceContext;
import by.ginger.smarthome.el.context.EvaluationContext;
import by.ginger.smarthome.el.context.SimpleEvaluationContext;
import by.ginger.smarthome.el.expression.ExpressionAware;
import by.ginger.smarthome.el.expression.DeviceAware;
import by.ginger.smarthome.el.expression.logic.AndExpression;
import by.ginger.smarthome.el.expression.Expression;
import by.ginger.smarthome.el.expression.ValueAware;
import by.ginger.smarthome.el.expression.actuator.ActuatorOffExpression;
import by.ginger.smarthome.el.expression.actuator.ActuatorOnExpression;
import by.ginger.smarthome.el.expression.actuator.AdjustableValueAboveExpression;
import by.ginger.smarthome.el.expression.actuator.AdjustableValueAboveOrEqualsExpression;
import by.ginger.smarthome.el.expression.actuator.AdjustableValueBelowExpression;
import by.ginger.smarthome.el.expression.actuator.AdjustableValueBelowOrEqualsExpression;
import by.ginger.smarthome.el.expression.actuator.AdjustableValueEqualsExpression;
import by.ginger.smarthome.el.expression.alarm.AlarmExpression;
import by.ginger.smarthome.el.expression.alarm.NotAlarmExpression;
import by.ginger.smarthome.el.expression.logic.OrExpression;
import by.ginger.smarthome.el.expression.device.PresentExpression;
import by.ginger.smarthome.el.expression.sensor.ValueAboveExpression;
import by.ginger.smarthome.el.expression.sensor.ValueAboveOrEqualsExpression;
import by.ginger.smarthome.el.expression.sensor.ValueBelowExpression;
import by.ginger.smarthome.el.expression.sensor.ValueBelowOrEqualsExpression;
import by.ginger.smarthome.el.expression.sensor.ValueEqualsExpression;
import by.ginger.smarthome.el.util.NumberUtils;
import by.ginger.smarthome.network.exception.NetworkException;
import by.ginger.smarthome.provider.device.Device;
import by.ginger.smarthome.provider.device.DeviceType;
import by.ginger.smarthome.provider.device.exception.DeviceException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author mirash
 */
public class SimpleExpressionParser implements ExpressionParser {

    private EvaluationTemplate evaluationTemplate = new StandardEvaluationTemplate();
    private final DeviceContext deviceContext;
    private final EvaluationContext evaluationContext;
    private Deque<Object> vals = new ArrayDeque<Object>();
    private Deque<Device> devices = new ArrayDeque<Device>();
    private Deque<Expression> transExpr = new ArrayDeque<Expression>();
    private Deque<Expression> internalExpr = new ArrayDeque<Expression>();
    private List<ExpressionProcessor> processors = new LinkedList<ExpressionProcessor>();

    public SimpleExpressionParser(DeviceContext deviceContext) {
        this.deviceContext = deviceContext;
        this.evaluationContext = new SimpleEvaluationContext(deviceContext.getNetworkManager());
        processors.add(new SecludingExpressionProcessor());
    }

    private void eval() throws DeviceException, NetworkException {
        Expression lastExpression = transExpr.pop();

        if (lastExpression instanceof ValueAware) {
            ValueAware valueAware = (ValueAware) lastExpression;
            Object value = vals.pop();
            valueAware.setValue(value);
        }

        if (lastExpression instanceof DeviceAware) {
            DeviceAware deviceAware = (DeviceAware) lastExpression;
            Device lastDevice = devices.pop();
            deviceAware.setDevice(lastDevice);
        }

        if (lastExpression instanceof ExpressionAware) {
            ExpressionAware expressionAware = (ExpressionAware) lastExpression;

            Expression anotherExpr = internalExpr.pop();
            Expression oneExpr = internalExpr.pop();

            expressionAware.setExprOne(oneExpr);
            expressionAware.setExprAnother(anotherExpr);
        }

        internalExpr.push(lastExpression);
    }

    private void processArgument(String element) {
        if (NumberUtils.isNumber(element)) {
            vals.add(Double.valueOf(element));
            return;
        }

        Device device = deviceContext.getDevice(element);
        if (device == null) {
            throw new RuntimeException("Element [" + element + "] is not defined");
        }
        devices.add(device);
    }

    private DeviceType getLastDeviceType() {
        Device device = devices.peek();
        return device.getDeviceType();
    }

    @Override
    public Expression parse(String expressionStr) throws DeviceException, NetworkException {
        String expressionProcessed = expressionStr;
        for (ExpressionProcessor processor : processors) {
            expressionProcessed = processor.preProcess(expressionProcessed);
        }

        StringTokenizer tokenizer = new StringTokenizer(expressionProcessed, ExpressionEntities.DELIM);
        while (tokenizer.hasMoreElements()) {
            String element = tokenizer.nextToken();

            if (element.equals(evaluationTemplate.getEvaluationStart())) {
                continue;
            }

            if (element.equals(evaluationTemplate.getEvaluationEnd())) {
                eval();
                continue;
            }

            switch (element) {
                case "(":
                    break;
                case "present":
                case "PRESENT":
                    transExpr.push(new PresentExpression(evaluationContext));
                    break;
                case "and":
                case "AND":
                    transExpr.push(new AndExpression(evaluationContext));
                    break;
                case "or":
                case "OR":
                    transExpr.push(new OrExpression(evaluationContext));
                    break;
                case ">":
                case "above":
                case "gt":
                case "ABOVE": {
                    DeviceType lastDeviceType = getLastDeviceType();
                    if (lastDeviceType == DeviceType.Sensor) {
                        transExpr.push(new ValueAboveExpression(evaluationContext));
                    } else if (lastDeviceType == DeviceType.Actuator) {
                        transExpr.push(new AdjustableValueAboveExpression(evaluationContext));
                    }
                    break;
                }
                case ">=": {
                    DeviceType lastDeviceType = getLastDeviceType();
                    if (lastDeviceType == DeviceType.Sensor) {
                        transExpr.push(new ValueAboveOrEqualsExpression(evaluationContext));
                    } else if (lastDeviceType == DeviceType.Actuator) {
                        transExpr.push(new AdjustableValueAboveOrEqualsExpression(evaluationContext));
                    }
                    break;
                }
                case "<":
                case "lt":
                case "less":
                case "LESS":
                case "below":
                case "BELOW": {
                    DeviceType lastDeviceType = getLastDeviceType();
                    if (lastDeviceType == DeviceType.Sensor) {
                        transExpr.push(new ValueBelowExpression(evaluationContext));
                    } else if (lastDeviceType == DeviceType.Actuator) {
                        transExpr.push(new AdjustableValueBelowExpression(evaluationContext));
                    }
                    break;
                }
                case "<=": {
                    DeviceType lastDeviceType = getLastDeviceType();
                    if (lastDeviceType == DeviceType.Sensor) {
                        transExpr.push(new ValueBelowOrEqualsExpression(evaluationContext));
                    } else if (lastDeviceType == DeviceType.Actuator) {
                        transExpr.push(new AdjustableValueBelowOrEqualsExpression(evaluationContext));
                    }
                    break;
                }
                case "==":
                case "equals":
                case "EQUALS": {
                    DeviceType lastDeviceType = getLastDeviceType();
                    if (lastDeviceType == DeviceType.Sensor) {
                        transExpr.push(new ValueEqualsExpression(evaluationContext));
                    } else if (lastDeviceType == DeviceType.Actuator) {
                        transExpr.push(new AdjustableValueEqualsExpression(evaluationContext));
                    }
                    break;
                }
                case "on":
                case "ON":
                    transExpr.push(new ActuatorOnExpression(evaluationContext));
                case "off":
                case "OFF":
                    transExpr.push(new ActuatorOffExpression(evaluationContext));
                case "alarm":
                case "ALARM":
                    transExpr.push(new AlarmExpression(evaluationContext));
                case "!alarm":
                case "!ALARM":
                    transExpr.push(new NotAlarmExpression(evaluationContext));
                case ")":
                    eval();
                    break;
                default:
                    processArgument(element);
            }

        }

        return internalExpr.pop();
    }

    public void setEvaluationTemplate(EvaluationTemplate evaluationTemplate) {
        this.evaluationTemplate = evaluationTemplate;
    }

    public void addProcessor(ExpressionProcessor processor) {
        processors.add(processor);
    }
}
