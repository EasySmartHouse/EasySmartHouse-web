/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.device;

import javax.script.Invocable;
import javax.script.ScriptException;
import net.easysmarthouse.provider.device.DeviceType;
import net.easysmarthouse.scripting.ScriptSource;

/**
 *
 * @author rusakovich
 */
public class ScriptableDevicePrototype implements ScriptableDevice {

    protected Invocable inv;
    protected Object obj;
    protected ScriptSource scriptSource;

    public Object getField(String fieldName) {
        StringBuilder builder = new StringBuilder(CONTEXT_DEVICE_NAME)
                .append(".").append(fieldName);
        try {
            return scriptSource.getScriptEngine().eval(builder.toString());
        } catch (ScriptException ex) {
            return null;
        }
    }

    public DeviceType getDeviceType() {
        return DeviceType.valueOf((String) getField("deviceType"));
    }

    public Object invoke(String methodName) {
        try {
            return inv.invokeMethod(obj, methodName);
        } catch (javax.script.ScriptException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchMethodException ex) {
            return null;
        }
    }

    @Override
    public void bind(ScriptSource scriptSource) {
        this.scriptSource = scriptSource;
        try {
            scriptSource.getScriptEngine().eval(scriptSource.getScript());
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        this.inv = (Invocable) scriptSource.getScriptEngine();
        this.obj = scriptSource.getScriptEngine().get(CONTEXT_DEVICE_NAME);
    }

    @Override
    public void unbind() {
        if (scriptSource != null) {
            scriptSource.getScriptEngine().put(CONTEXT_DEVICE_NAME, null);
        }
    }

}
