/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.sheduler;

import by.ginger.smarthome.network.NetworkManager;
import by.ginger.smarthome.network.exception.NetworkException;
import by.ginger.smarthome.sheduler.executor.CycleTaskExecutor;
import by.ginger.smarthome.sheduler.executor.QueueTaskExecutor;
import by.ginger.smarthome.sheduler.task.DefaultTaskProperties;
import by.ginger.smarthome.sheduler.task.Task;
import by.ginger.smarthome.sheduler.task.TaskContainer;
import by.ginger.smarthome.sheduler.task.TaskProcessor;
import by.ginger.smarthome.sheduler.task.TaskProperties;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author rusakovich
 */
public class SimpleSchedulerImpl implements Scheduler, QueueTaskExecutor, CycleTaskExecutor {

    private static final Log logger = LogFactory.getLog(SimpleSchedulerImpl.class);

    protected final BlockingQueue<TaskContainer> taskQueue;
    protected final List<TaskContainer> cycleTasks;

    protected TaskProcessor taskProcessor;

    protected NetworkManager networkManager;

    /**
     * Public constructor, create queue.
     */
    public SimpleSchedulerImpl() {
        taskQueue = new LinkedBlockingQueue<TaskContainer>();
        cycleTasks = new LinkedList<TaskContainer>();
    }

    @Override
    public void addTask(Task task) {
        this.addTask(task, new DefaultTaskProperties());
    }

    protected boolean isCompleted(TaskContainer taskContainer) {
        return taskProcessor.isTaskCompleted(taskContainer);
    }

    protected boolean isCycleTask(TaskContainer taskContainer) {
        return taskContainer.getProperties().isRepeat();
    }

    /**
     * Execute next task from queue.
     */
    @Override
    public void executeNextTask() {
        try {
            TaskContainer container = taskQueue.take();
            Task task = container.getTask();
            executeTask(task);
        } catch (InterruptedException ex) {
            logger.error("Queque task execution failed.", ex);
        }
    }

   void pushCompletedCycleTasks() {
        synchronized (cycleTasks) {
            Iterator<TaskContainer> iterator = cycleTasks.iterator();
            while (iterator.hasNext()) {
                TaskContainer container = iterator.next();
                if (isCompleted(container)) {
                    if (!isCycleTask(container)) {
                        iterator.remove();
                    }
                    taskProcessor.updateTask(container);
                    try {
                        taskQueue.put(container);
                    } catch (InterruptedException ex) {
                        logger.error("Cycle task execution failed.", ex);
                    }
                }
            }
        }
    }

    /**
     * Analyze repeat tasks list and add in queue tasks that re ready for
     * execution.
     */
    @Override
    public void executeCycleTasks() {
        pushCompletedCycleTasks();
    }

    /**
     * Execute task in 1-Wire context.
     */
    @Override
    public void executeTask(Task task) {
        task.setNetworkManager(networkManager);
        try {
            networkManager.startSession();
            task.execute();
            networkManager.endSession();
        } catch (NetworkException ex) {
            logger.error("Task execution failed.", ex);
        }

    }

    @Override
    public void addTask(Task task, TaskProperties properties) {
        try {
            TaskContainer container = new TaskContainer(task, properties);
            if (this.isCycleTask(container)) {
                taskProcessor.updateTask(container);
                synchronized (cycleTasks) {
                    cycleTasks.add(container);
                }
            }
            taskQueue.put(container);
        } catch (InterruptedException e) {
            logger.error("Task wait interrupted", e);
        }
    }

    public TaskProcessor getTaskProcessor() {
        return taskProcessor;
    }

    public void setTaskProcessor(TaskProcessor taskProcessor) {
        this.taskProcessor = taskProcessor;
    }

    public NetworkManager getNetworkManager() {
        return networkManager;
    }

    public void setNetworkManager(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

}
