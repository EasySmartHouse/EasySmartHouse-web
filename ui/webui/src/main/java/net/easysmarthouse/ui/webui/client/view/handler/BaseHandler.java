/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.view.handler;

import net.easysmarthouse.ui.webui.client.rpc.ServiceLocator;

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
