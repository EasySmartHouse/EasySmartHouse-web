/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.device.decorators;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import net.easysmarthouse.scripting.device.DeviceScriptBinding;
import javax.script.Invocable;
import javax.script.ScriptException;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.exception.DeviceException;
import net.easysmarthouse.scripting.ScriptSource;

/**
 *
 * @author rusakovich
 */
public class DeviceInvocationHandler extends DeviceScriptBinding<Device> implements InvocationHandler {

    private Invocable inv;
    private Object obj;

    public DeviceInvocationHandler(Device device) {
        super(device);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        try {
            return inv.invokeMethod(obj, methodName);
        } catch (ScriptException ex) {
            throw new DeviceException(ex);
        } catch (NoSuchMethodException ex) {
            return method.invoke(device, args);
        }
    }

    @Override
    public void bind(ScriptSource scriptSource) {
        super.bind(scriptSource);
        this.inv = (Invocable) scriptSource.getScriptEngine();
        this.obj = bindingScope.get(CONTEXT_DEVICE_NAME);
    }

    @Override
    public void unbind() {
        super.unbind();
        if (bindingScope != null) {
            bindingScope.remove(CONTEXT_DEVICE_NAME);
        }
    }

}
