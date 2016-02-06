package by.ginger.smarthome.maxim;

import by.ginger.smarthome.sheduler.SimpleSchedulerImpl;
import by.ginger.smarthome.sheduler.task.RefreshDevicesTask;
import by.ginger.smarthome.sheduler.task.TaskProperties;
import by.ginger.smarthome.sheduler.thread.CycleTaskThread;
import by.ginger.smarthome.sheduler.thread.QueueTaskThread;
import javax.annotation.Resource;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test.*;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-app-context.xml"})
public class ApplicationIntegrationTest{

    @Resource
    SimpleSchedulerImpl scheduler;

    @Test
    public void testThreadsExecution() throws InterruptedException {
        assertNotNull(scheduler);
        
        scheduler.addTask(new RefreshDevicesTask(), new TaskProperties(true, 200));
        
        CycleTaskThread cycleThread = new CycleTaskThread(true, scheduler);
        QueueTaskThread queueThread = new QueueTaskThread(true, scheduler);
        
        new Thread(cycleThread).start();
        final Thread execute = new Thread(queueThread);
        execute.start();
        
        Thread.sleep(3000);
        
        cycleThread.setContinueExecution(false);
        queueThread.setContinueExecution(false);
        
        execute.join(1000);
    }
}
