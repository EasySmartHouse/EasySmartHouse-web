/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.sheduler.task;

import by.ginger.smarthome.network.exception.NetworkException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author rusakovich
 */
public class RefreshDevicesTask extends BaseTask {

    private Log log = LogFactory.getLog(RefreshDevicesTask.class);

    @Override
    public void execute() {
        try {
            if (log.isDebugEnabled()) {
                log.debug("Search devices task started.");
            }
            networkManager.refreshDevices();
        } catch (NetworkException e) {
            log.error("Search devices task failed: ", e);
        }
    }

}
