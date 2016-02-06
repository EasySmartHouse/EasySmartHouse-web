/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.service.logging;

import java.io.Serializable;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.aop.support.AopUtils;

/**
 *
 * @author mirash
 */
abstract class BaseModuleLoggingInterceptor implements MethodInterceptor, Serializable {

    protected transient Logger defaultLogger = Logger.getLogger(getClass());
    private boolean hideProxyClassNames = false;

    public void setUseDynamicLogger(boolean useDynamicLogger) {
        this.defaultLogger = (useDynamicLogger ? null : Logger.getLogger(getClass()));
    }

    public void setLoggerName(String loggerName) {
        this.defaultLogger = Logger.getLogger(loggerName);
    }

    public void setHideProxyClassNames(boolean hideProxyClassNames) {
        this.hideProxyClassNames = hideProxyClassNames;
    }

    protected Logger getLoggerForInvocation(MethodInvocation invocation) {
        if (this.defaultLogger != null) {
            return this.defaultLogger;
        } else {
            Object target = invocation.getThis();
            return Logger.getLogger(getClassForLogging(target));
        }
    }

    protected Class getClassForLogging(Object target) {
        return (this.hideProxyClassNames ? AopUtils.getTargetClass(target) : target.getClass());
    }
}
