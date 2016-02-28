/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.service.logging;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

/**
 *
 * @author mirash
 */
public class SignalingModuleLoggingInterceptor extends BaseModuleLoggingInterceptor {

    public SignalingModuleLoggingInterceptor() {
        setUseDynamicLogger(true);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Logger logger = getLoggerForInvocation(mi);

        String address = (String) mi.getArguments()[0];
        boolean enabled = (boolean) mi.getArguments()[1];
        logger.info("Signaling element [" + address + "] has changed its enabled state to [" + enabled + "]");

        return mi.proceed();
    }
}
