/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.device;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.SimpleScriptContext;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.scripting.ScriptSource;

/**
 *
 * @author rusakovich
 */
public abstract class DeviceScriptBinding<D extends Device> implements ScriptableDevice {

    private static final String DELEGATE_CONTEXT_NAME = "delegate";

    protected D device;
    protected Bindings bindingScope;

    public DeviceScriptBinding(D device) {
        this.device = device;
    }

    @Override
    public void bind(ScriptSource scriptSource) {
        ScriptContext context = new SimpleScriptContext();
        this.bindingScope = context.getBindings(ScriptContext.ENGINE_SCOPE);
        bindingScope.put(DELEGATE_CONTEXT_NAME, device);

        try {
            scriptSource.getScriptEngine().eval(scriptSource.getScript(), context);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public void unbind() {
        if (bindingScope != null) {
            bindingScope.remove(DELEGATE_CONTEXT_NAME);
        }
    }

}
