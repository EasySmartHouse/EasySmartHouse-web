/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.el.expression.sensor;

import net.easysmarthouse.el.context.EvaluationContext;
import net.easysmarthouse.provider.device.sensor.Sensor;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 *
 * @author mirash
 */
public class ValueAboveExpressionTest {

    public ValueAboveExpressionTest() {
    }

    /**
     * Test of interpret method, of class ValueAboveExpression.
     */
    @Test
    public void testInterpret() throws Exception {
        System.out.println("***** interpret ****");
        EvaluationContext context = mock(EvaluationContext.class);
        ValueAboveExpression instance = new ValueAboveExpression(context);

        Sensor sensor = mock(Sensor.class);
        when(sensor.getValue()).thenReturn(45.44d);

        double value = 23.0d;
        instance.setValue(value);
        instance.setDevice(sensor);

        instance.interpret(context);
        verify(context).addEvaluationResult(instance, true);

        value = 77.0d;
        instance.setValue(value);
        instance.setDevice(sensor);

        instance.interpret(context);
        verify(context).addEvaluationResult(instance, false);
    }
}