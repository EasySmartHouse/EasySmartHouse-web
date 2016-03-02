/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting;

import javax.script.ScriptEngine;

/**
 *
 * @author rusakovich
 */
class NullScriptSource implements ScriptSource {

    @Override
    public String getScript() throws ScriptException {
        return null;
    }

    @Override
    public ScriptEngine getScriptEngine() {
        return null;
    }

}
