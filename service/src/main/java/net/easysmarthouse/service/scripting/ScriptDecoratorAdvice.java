/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.service.scripting;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.scripting.ScriptFileFilter;
import net.easysmarthouse.scripting.ScriptSource;
import net.easysmarthouse.scripting.ScriptSourceFactory;
import net.easysmarthouse.scripting.device.decorators.ProxyFactory;
import net.easysmarthouse.scripting.device.decorators.ScriptDeviceDecoratorProxyFactory;
import net.easysmarthouse.scripting.util.DeviceScriptUtil;
import net.easysmarthouse.scripting.util.FileHelper;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 *
 * @author rusakovich
 */
public class ScriptDecoratorAdvice implements MethodInterceptor, InitializingBean, DisposableBean {

    private Map<String, ScriptSource> scriptSources = new HashMap<>();
    private ProxyFactory deviceProxyFactory = ScriptDeviceDecoratorProxyFactory.getInstance();

    private String scriptFolder;
    private boolean enabled = false;

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object primaryResult = mi.proceed();
        if (!enabled) {
            return primaryResult;
        }

        if (primaryResult instanceof List) {
            if (((List) primaryResult).isEmpty()) {
                return primaryResult;
            }

            List<Device> devices = (List<Device>) primaryResult;
            List<Device> finalResult = new ArrayList<>(devices.size());
            for (Device device : devices) {
                if (scriptSources.containsKey(device.getAddress())) {
                    Device proxiedDevice = deviceProxyFactory.createProxy(device, scriptSources.get(device.getAddress()));
                    finalResult.add(proxiedDevice);
                } else {
                    finalResult.add(device);
                }
            }

            return finalResult;
        }

        if (primaryResult instanceof Device) {
            Device device = (Device) primaryResult;
            if (scriptSources.containsKey(device.getAddress())) {
                return deviceProxyFactory.createProxy(device, scriptSources.get(device.getAddress()));
            }
        }

        return primaryResult;
    }

    public void setScriptFolder(String scriptFolder) {
        this.scriptFolder = scriptFolder;
    }

    private String findAddress(String scriptContent) {
        return DeviceScriptUtil.getDeviceAddress(scriptContent);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (scriptFolder == null) {
            return;
        }

        try {
            String[] scriptNames = FileHelper.getFiles(scriptFolder, new ScriptFileFilter());
            if (scriptNames == null || scriptNames.length == 0) {
                return;
            }

            for (int i = 0; i < scriptNames.length; i++) {
                ScriptSource scriptSource = ScriptSourceFactory.createScriptResource(
                            scriptFolder + File.separator + scriptNames[i]);
                String deviceAddress = findAddress(scriptSource.getScript());
                if (deviceAddress != null) {
                    scriptSources.put(deviceAddress, scriptSource);
                }
            }
            
            enabled = true;
        } catch (IOException ex) {
            throw new IllegalStateException("Error while processing scripts", ex);
        }
    }

    @Override
    public void destroy() throws Exception {
        scriptSources.clear();
    }

}
