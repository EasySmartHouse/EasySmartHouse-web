/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.sheduler.task;

/**
 *
 * @author rusakovich
 */
public class TaskProperties {

    private boolean repeat;
    private long delay;

    public TaskProperties() {
    }

    public TaskProperties(boolean repeat) {
        super();
        this.repeat = repeat;
    }

    public TaskProperties(boolean repeat, long delay) {
        super();
        this.repeat = repeat;
        this.delay = delay;
    }

    /**
     * Check if task should be repeated.
     */
    public boolean isRepeat() {
        return repeat;
    }

    /**
     * Determine should task repeat or not.
     */
    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    /**
     * Get interval before task should be repeated.
     */
    public long getDelay() {
        return delay;
    }

    /**
     * Set time interval before two executions of the task
     *
     * @param delay the delay time in milliseconds
     */
    public void setDelay(long delay) {
        this.delay = delay;
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof TaskProperties) {
            TaskProperties anotherProperties = (TaskProperties) anObject;
            if (this.repeat != anotherProperties.repeat) {
                return false;
            }
            if (this.delay != anotherProperties.delay) {
                return false;
            }
            return true;
        }
        return false;
    }

}
