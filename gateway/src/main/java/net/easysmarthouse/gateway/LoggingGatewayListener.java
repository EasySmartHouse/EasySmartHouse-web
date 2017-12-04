/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.gateway;

import java.io.IOException;
import net.easysmarthouse.provider.device.gateway.GatewayListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author rusakovich
 */
public class LoggingGatewayListener implements GatewayListener {

    private static final Log log = LogFactory.getLog(LoggingGatewayListener.class);

    @Override
    public void receive(String message) throws IOException {
        log.info(message);
    }

}
