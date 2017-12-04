/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.provider.device.gateway;

import java.io.IOException;

/**
 *
 * @author rusakovich
 */
public interface GatewayListener {

    public void receive(String message) throws IOException;

}
