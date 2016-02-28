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
public class TimeManagerImpl implements TimeManager {

    /**
     * Return current time in millisecond, use
     * <code>System.currentTimeMillis()</code>
     *
     * @see System.currentTimeMillis
     */
    @Override
    public long getCurremtTime() {
        return System.currentTimeMillis();
    }

}
