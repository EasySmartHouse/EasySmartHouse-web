/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.sheduler.executor;

/**
 *
 * @author rusakovich
 */
public interface QueueTaskExecutor extends TaskExecutor {

    public void executeNextTask();

}
