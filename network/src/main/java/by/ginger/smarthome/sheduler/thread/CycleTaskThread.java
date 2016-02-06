/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.sheduler.thread;

import by.ginger.smarthome.sheduler.executor.CycleTaskExecutor;

/**
 *
 * @author rusakovich
 */
public class CycleTaskThread extends AbstractTaskThread implements Runnable {

    private static final long EXECUTION_DELAY_DEFAULT = 100;
    private long executionDelay = EXECUTION_DELAY_DEFAULT;

    private final CycleTaskExecutor executor;

    public CycleTaskThread(boolean continueExecution, CycleTaskExecutor executor) {
        super(continueExecution);
        this.executor = executor;
    }

    @Override
    public void run() {
        while (continueExecution) {
            executor.executeCycleTasks();
            try {
                Thread.sleep(executionDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setExecutionDelay(long executionDelay) {
        this.executionDelay = executionDelay;
    }

    public long getExecutionDelay() {
        return executionDelay;
    }

}
