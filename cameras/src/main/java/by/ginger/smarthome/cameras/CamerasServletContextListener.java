/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.cameras;

import by.ginger.smarthome.cameras.core.WebcamAccessor;
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
