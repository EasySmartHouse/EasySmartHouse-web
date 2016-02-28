/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.sheduler.thread;

/**
 *
 * @author rusakovich
 */
public abstract class AbstractTaskThread {

    protected boolean continueExecution;

    public AbstractTaskThread(boolean continueExecution) {
        this.continueExecution = continueExecution;
    }

    public boolean isContinueExecution() {
        return continueExecution;
    }

    public void setContinueExecution(boolean continueExecution) {
        this.continueExecution = continueExecution;
    }

}
