/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.device.decorators;

import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.scripting.ScriptSource;

/**
 *
 * @author rusakovich
 */
public interface ProxyFactory {

    public Device createProxy(Device target, ScriptSource scriptSource);

}
