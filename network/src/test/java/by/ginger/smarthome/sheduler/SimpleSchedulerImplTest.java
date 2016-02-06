/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.sheduler;

import by.ginger.smarthome.network.NetworkManager;
import by.ginger.smarthome.sheduler.task.Task;
import by.ginger.smarthome.sheduler.task.TaskContainer;
import by.ginger.smarthome.sheduler.task.TaskProcessor;
import by.ginger.smarthome.sheduler.task.TaskProcessorImpl;
import by.ginger.smarthome.sheduler.task.TaskProperties;
import by.ginger.smarthome.sheduler.task.TimeManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import junit.framework.TestCase;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

/**
 *
 * @author rusakovich
 */
public class SimpleSchedulerImplTest extends TestCase {

    public SimpleSchedulerImplTest(String testName) {
        super(testName);
    }

    protected SimpleSchedulerImpl scheduler;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        scheduler = new SimpleSchedulerImpl();

        TaskProcessor processor = mock(TaskProcessor.class);
        when(processor.isTaskCompleted(any(TaskContainer.class))).thenReturn(true);

        NetworkManager networkManager = mock(NetworkManager.class);

        scheduler.setNetworkManager(networkManager);
        scheduler.setTaskProcessor(processor);

    }

    protected void tearDown() throws Exception {
        super.tearDown();
        scheduler = null;
    }

    /**
     * Test of executeTask method, of class SimpleSchedulerImpl.
     */
    public void testExecuteTask() {
        System.out.println("**** executeTask *****");
        Task task = mock(Task.class);

        scheduler.addTask(task);
        scheduler.executeNextTask();

        verify(task).execute();
    }

    public void testSeveralTasksExecution() {
        System.out.println("**** severalTasksExecution *****");
        Task task1 = mock(Task.class);
        Task task2 = mock(Task.class);
        Task task3 = mock(Task.class);

        Mockito.doNothing().when(task1).execute();
        Mockito.doNothing().when(task2).execute();
        Mockito.doNothing().when(task3).execute();

        scheduler.addTask(task1);
        scheduler.addTask(task2);
        scheduler.addTask(task3);

        scheduler.executeNextTask();
        scheduler.executeNextTask();
        scheduler.executeNextTask();

        InOrder inOrder = inOrder(task1, task2, task3);
        inOrder.verify(task1).execute();
        inOrder.verify(task2).execute();
        inOrder.verify(task3).execute();
    }

    public void testSingleCycleTaskExecution() {
        System.out.println("*** singleCycleTaskExecution ***");
        Task task = mock(Task.class);
        scheduler.addTask(task, new TaskProperties(true));

        Mockito.doNothing().when(task).execute();

        scheduler.executeNextTask();
        scheduler.pushCompletedCycleTasks();
        scheduler.executeNextTask();
        scheduler.pushCompletedCycleTasks();
        scheduler.executeNextTask();

        verify(task, times(3)).execute();
    }

    public void testSeveralTasksCycleSequence() {
          System.out.println("*** severalTasksCycleSequence ***");
        Task task1 = mock(Task.class);
        Task task2 = mock(Task.class);

        Mockito.doNothing().when(task1).execute();
        Mockito.doNothing().when(task2).execute();

        scheduler.addTask(task1, new TaskProperties(true));
        scheduler.addTask(task2);

        scheduler.executeNextTask();
        scheduler.pushCompletedCycleTasks();
        scheduler.executeNextTask();
        scheduler.pushCompletedCycleTasks();
        scheduler.executeNextTask();

        InOrder order = inOrder(task1, task2);
        order.verify(task1).execute();
        order.verify(task2).execute();
    }

    public void testMultiThreadsTasksExecution() throws InterruptedException {
        System.out.println("*** multiThreadsTasksExecution ***");
        Task task1 = mock(Task.class);
        Task task2 = mock(Task.class);

        Thread executionThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    scheduler.executeNextTask();
                }
            }
        });
        executionThread.start();

        scheduler.addTask(task1, new TaskProperties(true));
        scheduler.addTask(task2);

        Thread.sleep(100);
        scheduler.pushCompletedCycleTasks();
        Thread.sleep(100);
        scheduler.pushCompletedCycleTasks();

        executionThread.join(5000);//main thread wait when executionThread ends (call dependThread.join() in main thread)

        InOrder order = inOrder(task1, task2);
        order.verify(task1).execute();
        order.verify(task2).execute();
    }

    private List<TaskContainer> queueToList(Queue<TaskContainer> queue) {
        List<TaskContainer> list = new ArrayList<TaskContainer>(queue.size());
        while (!queue.isEmpty()) {
            list.add(queue.poll());
        }
        return list;
    }

    public void testSeveralCycleTasksDelaySequence() throws InterruptedException {
        System.out.println("*** multiThreadsTasksExecution ***");
        Task task1 = mock(Task.class);
        Task task2 = mock(Task.class);

        TaskProcessorImpl processor = new TaskProcessorImpl();

        TimeManager timeManager = mock(TimeManager.class);
        when(timeManager.getCurremtTime()).thenReturn(0L, 1L, 103L, 103L, 105L, 203L, 203L);

        processor.setTimeManager(timeManager);
        scheduler.setTaskProcessor(processor);

        scheduler.addTask(task1, new TaskProperties(true, 200L));
        scheduler.addTask(task2, new TaskProperties(true, 100L));

        scheduler.executeCycleTasks();
        scheduler.executeCycleTasks();

        List<Task> expected = new ArrayList<Task>(4);
        expected.add(task1);
        expected.add(task2);
        expected.add(task2);
        expected.add(task1);

        List<Task> actual = new ArrayList<Task>(4);
        List<TaskContainer> list = queueToList(scheduler.taskQueue);
        for (TaskContainer taskContainer : list) {
            actual.add(taskContainer.getTask());
        }
        assertEquals(expected, actual);

    }

}
