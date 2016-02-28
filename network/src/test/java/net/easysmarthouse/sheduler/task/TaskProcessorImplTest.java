/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.sheduler.task;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author rusakovich
 */
public class TaskProcessorImplTest extends TestCase {

    public TaskProcessorImplTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testUpdateTask() {
        TimeManager timeManager = mock(TimeManager.class);
        when(timeManager.getCurremtTime()).thenReturn(10L);

        TaskProperties properties = mock(TaskProperties.class);
        when(properties.getDelay()).thenReturn(20L);

        TaskContainer container = mock(TaskContainer.class);
        when(container.getProperties()).thenReturn(properties);

        TaskProcessorImpl timeProcessor = new TaskProcessorImpl();
        timeProcessor.setTimeManager(timeManager);
        timeProcessor.updateTask(container);

        verify(container).setStartTime(30);
    }

    public void testIsTaskCompleted() {
        TimeManager timeManager = mock(TimeManager.class);
        when(timeManager.getCurremtTime()).thenReturn(10L, 20L, 40L, 60L);

        TaskProperties properties = mock(TaskProperties.class);
        when(properties.getDelay()).thenReturn(20L);

        TaskContainer container = mock(TaskContainer.class);
        when(container.getProperties()).thenReturn(properties);
        when(container.getStartTime()).thenReturn(40L);

        TaskProcessorImpl timeProcessor = new TaskProcessorImpl();
        timeProcessor.setTimeManager(timeManager);

        assertFalse("Early task execution", timeProcessor.isTaskCompleted(container));
        assertFalse("Early task execution", timeProcessor.isTaskCompleted(container));

        assertTrue("Late task execution", timeProcessor.isTaskCompleted(container));
        assertTrue("Late task execution", timeProcessor.isTaskCompleted(container));
    }

}
