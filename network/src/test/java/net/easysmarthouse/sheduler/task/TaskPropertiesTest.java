/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.sheduler.task;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 *
 * @author rusakovich
 */
public class TaskPropertiesTest extends TestCase {

    public TaskPropertiesTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testNullEquals() {
        System.out.println("*** testNullEquals ***");
        TaskProperties properties = new TaskProperties();
        Assert.assertFalse(properties.equals(null));
    }

    public void testSameObject() {
        System.out.println("*** testSameObject ***");
        TaskProperties properties = new TaskProperties();
        Assert.assertTrue(properties.equals(properties));
    }

    public void testEquals() {
        System.out.println("***  testEquals ***");
        int firstInterval = 10;

        TaskProperties first = new TaskProperties();
        first.setRepeat(false);
        first.setDelay(firstInterval);

        TaskProperties second = new TaskProperties();
        second.setRepeat(false);
        second.setDelay(firstInterval);

        Assert.assertTrue(first.equals(second));

        final int secondInterval = 10;

        first = new TaskProperties();
        first.setRepeat(true);
        first.setDelay(secondInterval);

        second = new TaskProperties();
        second.setRepeat(true);
        second.setDelay(secondInterval);

        Assert.assertTrue(first.equals(second));
    }

    public void testNotEqual() {
        System.out.println("***  testNotEqual ***");
        TaskProperties first = new TaskProperties();
        first.setRepeat(true);
        
        TaskProperties second = new TaskProperties();
        second.setRepeat(false);
        
        Assert.assertFalse(first.equals(second));

        
        first = new TaskProperties();
        first.setDelay(10);
        
        second = new TaskProperties();
        second.setDelay(20);
        
        Assert.assertFalse(first.equals(second));
    }

}
