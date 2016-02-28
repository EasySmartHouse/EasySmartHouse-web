/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.el.expression.actuator;

import net.easysmarthouse.el.context.EvaluationContext;
import net.easysmarthouse.provider.device.actuator.AdjustableActuator;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author rusakovich
 */
public class AdjustableValueAboveOrEqualsExpressionTest {

    public AdjustableValueAboveOrEqualsExpressionTest() {
    }

    /**
     * Test of interpret method, of class
     * AdjustableValueAboveOrEqualsExpression.
     */
    @Test
    public void testInterpret() throws Exception {
        System.out.println("***** interpret *****");
        EvaluationContext context = mock(EvaluationContext.class);
        AdjustableValueAboveOrEqualsExpression instance = new AdjustableValueAboveOrEqualsExpression(context);

        AdjustableActuator actuator = mock(AdjustableActuator.class);
        when(actuator.getState()).thenReturn(50d);

        double value = 23.0d;
        instance.setValue(value);
        instance.setDevice(actuator);

        instance.interpret(context);
        verify(context).addEvaluationResult(instance, true);

        value = 77.0d;
               instance.setValue(value);
        instance.setDevice(actuator);

        instance.interpret(context);
        verify(context).addEvaluationResult(instance, false);
    }

}
