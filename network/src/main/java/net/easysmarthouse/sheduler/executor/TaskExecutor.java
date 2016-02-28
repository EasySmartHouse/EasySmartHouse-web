/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.sheduler.executor;

import net.easysmarthouse.sheduler.task.Task;

/**
 *
 * @author rusakovich
 */
public interface TaskExecutor {

    public void executeTask(Task task);

}
