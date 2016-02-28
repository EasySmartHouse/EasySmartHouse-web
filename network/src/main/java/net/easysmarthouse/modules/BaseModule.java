/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.modules;

import net.easysmarthouse.sheduler.Scheduler;

/**
 *
 * @author rusakovich
 * @param <D> - device description
 */
public abstract class BaseModule<D> {

    protected Scheduler scheduler;
    protected long taskDelay;

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public long getTaskDelay() {
        return taskDelay;
    }

    public void setTaskDelay(long taskDelay) {
        this.taskDelay = taskDelay;
    }
    
    public abstract void initModule();

}
