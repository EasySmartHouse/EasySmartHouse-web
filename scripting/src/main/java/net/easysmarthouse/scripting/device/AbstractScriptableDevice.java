/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.device;

import javax.script.ScriptEngine;

/**
 *
 * @author rusakovich
 */
public abstract class AbstractScriptableDevice implements ScriptableDevice {

    protected ScriptEngine scriptEngine;

    @Override
    public void setScriptEngine(ScriptEngine scriptEngine) {
        this.scriptEngine = scriptEngine;
    }

}
