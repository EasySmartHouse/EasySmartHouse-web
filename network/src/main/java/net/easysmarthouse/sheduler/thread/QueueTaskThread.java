/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.sheduler.thread;

import net.easysmarthouse.sheduler.executor.QueueTaskExecutor;

/**
 *
 * @author rusakovich
 */
public class QueueTaskThread extends AbstractTaskThread implements Runnable {

    private final QueueTaskExecutor executor;

    public QueueTaskThread(boolean continueExecution, QueueTaskExecutor executor) {
        super(continueExecution);
        this.executor = executor;
    }

    @Override
    public void run() {
        while (continueExecution) {
            executor.executeNextTask();
        }
    }

}
