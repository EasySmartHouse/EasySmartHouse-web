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
public interface TaskProcessor {

    /**
     * Calculate task start time.
     */
    public void updateTask(TaskContainer taskContainer);

    /**
     * Check if task should execute now.
     */
    public boolean isTaskCompleted(TaskContainer taskContainer);
}
