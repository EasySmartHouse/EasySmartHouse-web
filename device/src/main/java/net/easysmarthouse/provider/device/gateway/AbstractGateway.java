/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.provider.device.gateway;

/**
 *
 * @author rusakovich
 */
public abstract class AbstractGateway implements Gateway {

    private GatewayListener listener = new NullGatewayListener();

    @Override
    public void setGatewayListener(GatewayListener listener) {
        this.listener = listener;
    }

    public GatewayListener getGatewayListener() {
        return listener;
    }

}
