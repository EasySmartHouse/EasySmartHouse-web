/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.device.decorators;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.scripting.device.AbstractScriptableDevice;

/**
 *
 * @author rusakovich
 */
public abstract class DeviceScriptDecorator<D extends Device> extends AbstractScriptableDevice {

    private String script;
    protected D device;
    protected Bindings bindingScope;

    public DeviceScriptDecorator(D device) {
        this.device = device;
    }

    public void setScript(String script) {
        this.script = script;
    }

    @Override
    public void setScriptEngine(ScriptEngine scriptEngine) {
        super.setScriptEngine(scriptEngine);

        ScriptContext context = new SimpleScriptContext();
        this.bindingScope = context.getBindings(ScriptContext.ENGINE_SCOPE);
        bindingScope.put("delegate", device);

        try {
            scriptEngine.eval(script, context);
        } catch (ScriptException ex) {
            throw new IllegalStateException(ex);
        }

    }

}
