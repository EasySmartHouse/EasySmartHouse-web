/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.network;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.script.ScriptContext;
import net.easysmarthouse.network.AbstractStorableNetworkManager;
import net.easysmarthouse.network.exception.NetworkException;
import net.easysmarthouse.network.extension.ConversionExtension;
import net.easysmarthouse.network.extension.IdleConversionExtension;
import net.easysmarthouse.network.util.AddressHelper;
import net.easysmarthouse.provider.device.Closeable;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.converter.DeviceConverter;
import net.easysmarthouse.scripting.ScriptFileFilter;
import net.easysmarthouse.scripting.ScriptSource;
import net.easysmarthouse.scripting.ScriptSourceFactory;
import net.easysmarthouse.scripting.device.ScriptableDevice;
import net.easysmarthouse.scripting.device.ScriptableDevicePrototype;
import net.easysmarthouse.scripting.device.converter.ScriptableDeviceConverter;
import net.easysmarthouse.scripting.util.FileHelper;

/**
 *
 * @author rusakovich
 */
public class ScriptingNetworkManager extends AbstractStorableNetworkManager {

    private final ConversionExtension conversionExtension = new IdleConversionExtension();
    
    private Map<Device, ScriptSource> scriptSources = new HashMap<Device, ScriptSource>();
    private DeviceConverter deviceConverter = new ScriptableDeviceConverter();
    private final String scriptFolder;

    public ScriptingNetworkManager(String scriptFolder) {
        this.scriptFolder = scriptFolder;
    }

    @Override
    public void init() {
        File[] scripts = null;
        try {
            scripts = FileHelper.getFiles(scriptFolder, new ScriptFileFilter());
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
        if (scripts != null && scripts.length != 0) {

            for (int i = 0; i < scripts.length; i++) {
                File script = scripts[i];
                ScriptSource scriptSource = ScriptSourceFactory.createScriptResource(
                        scriptFolder + File.separator + script.getName());

                ScriptableDevicePrototype prototype = new ScriptableDevicePrototype();
                prototype.bind(scriptSource);

                Device device = deviceConverter.getDevice(prototype);
                scriptSources.put(device, scriptSource);

                devices.add(device);
            }

        }
    }

    @Override
    public void destroy() {
        for (ScriptSource scriptSource : scriptSources.values()) {
            scriptSource.getScriptEngine().getBindings(ScriptContext.ENGINE_SCOPE).clear();
            scriptSource.getScriptEngine().getBindings(ScriptContext.GLOBAL_SCOPE).clear();
        }

        for (Device device : devices) {
            if (device instanceof Closeable) {
                ((Closeable) device).close();
            }
        }
        devices.clear();
    }

    @Override
    public void startSession() throws NetworkException {
    }

    @Override
    public void endSession() throws NetworkException {
    }

    @Override
    public void refreshDevices() throws NetworkException {
        for (Device device : devices) {
            if (device instanceof ScriptableDevice) {
                ScriptableDevice scriptable = (ScriptableDevice) device;
                synchronized (scriptable) {
                    ScriptSource scriptSource = scriptSources.get(device);

                    scriptable.unbind();
                    scriptable.bind(scriptSource);
                }
            }

        }
    }

    @Override
    public List<Device> getDevices() throws NetworkException {
        return devices;
    }

    @Override
    public List<Long> getDevicesAddresses() throws NetworkException {
        List<Long> addresses = new ArrayList<>();
        for (Device device : devices) {
            long addr = AddressHelper.buidldHash(device.getAddress(), 127l);
            addresses.add(addr);
        }
        return addresses;
    }

    @Override
    public boolean isDevicePresent(String address) throws NetworkException {
        for (Device device : devices) {
            if (device.getAddress().equalsIgnoreCase(address)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ConversionExtension getConversionExtension() {
        return conversionExtension;
    }

}
