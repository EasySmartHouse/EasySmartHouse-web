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
public class ActuatorModuleLoggingInterceptor extends BaseModuleLoggingInterceptor {

    public ActuatorModuleLoggingInterceptor() {
        setUseDynamicLogger(true);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Logger logger = getLoggerForInvocation(mi);

        String address = (String) mi.getArguments()[0];
        Object newValue = mi.getArguments()[1];

        logger.info("Actuator [" + address + "] has changed its state to [" + newValue + "]");

        return mi.proceed();
    }
}
