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
public class TaskContainer {

    private Task task;
    private TaskProperties properties;
    private long startTime;

    public TaskContainer(Task task, TaskProperties properties) {
        this.properties = properties;
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public TaskProperties getProperties() {
        return properties;
    }

    public void setProperties(TaskProperties properties) {
        this.properties = properties;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

}
