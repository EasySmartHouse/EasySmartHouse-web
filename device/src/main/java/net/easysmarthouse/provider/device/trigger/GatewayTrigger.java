/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.provider.device.trigger;

import net.easysmarthouse.provider.device.gateway.Gateway;

/**
 *
 * @author rusakovich
 */
public interface GatewayTrigger {

    public String getSendingMessage();

    public Gateway getGateway();

}
