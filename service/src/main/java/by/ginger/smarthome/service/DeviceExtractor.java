/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.service;

import by.ginger.smarthome.network.NetworkManager;
import by.ginger.smarthome.provider.device.Device;
import by.ginger.smarthome.provider.device.IdleDevice;
import java.lang.reflect.Method;
import java.util.List;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.util.ReflectionUtils;

/**
 *
 * @author mirash
 */
public class DeviceExtractor extends AbstractFactoryBean<Device> {

    private NetworkManager networkManager;
    private Class<?> deviceClass;
    private String address;
    private String initMethodName;

    @Override
    public Class<?> getObjectType() {
        return deviceClass;
    }

    private void invokeInitMethodIfNeed(Object target) {
        if (initMethodName != null) {
            Method initMethod = ReflectionUtils.findMethod(target.getClass(), initMethodName);
            if (initMethod != null) {
                ReflectionUtils.invokeMethod(initMethod, target);
            }
        }
    }

    @Override
    protected Device createInstance() throws Exception {
        List<Device> devices = networkManager.getDevices();
        for (Device device : devices) {
            if (device.getAddress().equalsIgnoreCase(address)) {
                invokeInitMethodIfNeed(device);
                return device;
            }
        }
        return new IdleDevice();
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setClass(Class<?> deviceClass) {
        this.deviceClass = deviceClass;
    }

    public void setNetworkManager(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }
}
