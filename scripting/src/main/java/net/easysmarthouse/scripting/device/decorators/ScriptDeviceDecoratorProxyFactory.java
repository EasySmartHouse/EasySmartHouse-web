/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.device.decorators;

import java.lang.reflect.Proxy;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.DeviceType;
import net.easysmarthouse.provider.device.actuator.Actuator;
import net.easysmarthouse.provider.device.alarm.SignalingElement;
import net.easysmarthouse.provider.device.sensor.Sensor;
import net.easysmarthouse.scripting.ScriptSource;

/**
 *
 * @author rusakovich
 */
public class ScriptDeviceDecoratorProxyFactory implements ProxyFactory {

    private ScriptDeviceDecoratorProxyFactory() {
    }

    @Override
    public Device createProxy(Device target, ScriptSource scriptSource) {
        Class<?> targetInterface = null;

        DeviceType deviceType = target.getDeviceType();
        switch (deviceType) {
            case Sensor:
                targetInterface = Sensor.class;
                break;
            case Actuator:
                targetInterface = Actuator.class;
                break;
            case Key:
                targetInterface = SignalingElement.class;
                break;
            default:
                throw new IllegalStateException("Cannot create proxy for device: " + target.getAddress());
        }
        
        if (targetInterface == null){
            throw new IllegalStateException("Cannot find appropriate interface for device: " + target.getClass());
        }

        DeviceInvocationHandler handler = new DeviceInvocationHandler(target);
        handler.bind(scriptSource);

        return (Device) Proxy.newProxyInstance(
                targetInterface.getClassLoader(),
                new Class[]{targetInterface},
                handler);
    }

    private static class InstanceHolder {

        private static final ProxyFactory INSTANCE = new ScriptDeviceDecoratorProxyFactory();
    }

    public static ProxyFactory getInstance() {
        return InstanceHolder.INSTANCE;
    }

}
