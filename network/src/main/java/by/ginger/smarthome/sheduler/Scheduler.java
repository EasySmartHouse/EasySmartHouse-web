/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.sheduler;

import by.ginger.smarthome.sheduler.task.Task;
import by.ginger.smarthome.sheduler.task.TaskProperties;

/**
 *
 * @author rusakovich
 */
public interface Scheduler {

    /**
     * Add task in queue. This task will be executed later in 1-Wire context.
     *
     * @param task
     */
    void addTask(Task task);

    /**
     * Add task in queue. This task will be executed later in 1-Wire context.
     *
     * @param task
     * @param properties bean object that should contain task execution
     * properties
     */
    void addTask(Task task, TaskProperties properties);
}
