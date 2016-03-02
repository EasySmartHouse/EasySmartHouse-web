/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rusakovich
 */
public class ScriptResourceFactoryTest {
    
    public ScriptResourceFactoryTest() {
    }
    
    /**
     * Test of createScriptResource method, of class ScriptResourceFactory.
     */
    @Test
    public void testCreateScriptResource() throws ScriptException {
        System.out.println("***** createScriptResource *****");
        ScriptSource result = ScriptSourceFactory.createScriptResource("file:src/test/resources/sensor1.js");
        assertNotNull(result);
        assertNotNull(result.getScript());
    }
    
}
