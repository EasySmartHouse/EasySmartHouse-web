/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.el.parser;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mirash
 */
public class SecludingExpressionProcessorTest {
    
    public SecludingExpressionProcessorTest() {
    }
    
    /**
     * Test of preProcess method, of class SecludingExpressionProcessor.
     */
    @Test
    public void testPreProcess() {
        System.out.println("***** preProcess *******");
        String expression = "$${(device1 present) and ((sensor2>34.44) and (sensor1<3.45))}";
        SecludingExpressionProcessor instance = new SecludingExpressionProcessor();
        
        String result = instance.preProcess(expression);
        String expResult = "$${ ( device1 present )  and  (  ( sensor2 > 34.44 )  and  ( sensor1 < 3.45 )  ) }";
        assertEquals(expResult, result);
       
    }

}