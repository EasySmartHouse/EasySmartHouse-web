/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.sheduler.task;

/**
 *
 * @author rusakovich
 */
public class TaskProcessorImpl implements TaskProcessor {

    protected TimeManager timeManager;

    /**
     * Check if current time is greater or equal than task start time.
     */
    @Override
    public boolean isTaskCompleted(TaskContainer taskContainer) {
        long currentTime = timeManager.getCurremtTime();
        return currentTime >= taskContainer.getStartTime();
    }

    /**
     * Update task start time. Use simple algorithm, add delay to current time.
     */
    @Override
    public void updateTask(TaskContainer taskContainer) {
        long currentTime = timeManager.getCurremtTime();
        long delay = taskContainer.getProperties().getDelay();
        long startTime = currentTime + delay;
        taskContainer.setStartTime(startTime);
    }

    public TimeManager getTimeManager() {
        return timeManager;
    }

    public void setTimeManager(TimeManager timeManager) {
        this.timeManager = timeManager;
    }
}
