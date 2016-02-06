/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.view.handler;

import by.ginger.smarthome.ui.webui.client.rpc.ServiceLocator;

/**
 *
 * @author mirash
 */
abstract class BaseHandler {

    protected ServiceLocator serviceLocator;

    BaseHandler() {
        serviceLocator = ServiceLocator.instance();
    }
}
