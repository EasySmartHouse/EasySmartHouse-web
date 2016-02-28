/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.cameras;

import net.easysmarthouse.cameras.core.WebcamAccessor;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author mirash
 */
public class CamerasServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        WebcamAccessor.getCamDeviceHandler().openAvailable();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        WebcamAccessor.getCamDeviceHandler().closeAll();
    }
}
