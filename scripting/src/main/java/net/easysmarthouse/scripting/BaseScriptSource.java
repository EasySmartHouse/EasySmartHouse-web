/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting;

import java.util.List;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import net.easysmarthouse.scripting.util.FileHelper;

/**
 *
 * @author rusakovich
 */
abstract class BaseScriptSource implements ScriptSource {

    protected final String filePath;
    protected ScriptEngine scriptEngine;

    private void createEngine() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptType scriptType = ScriptType.getScriptTypeByExt(FileHelper.getExtension(filePath));
        this.scriptEngine = manager.getEngineByName(scriptType.name());
    }

    public BaseScriptSource(String filePath) {
        this.filePath = filePath;
        createEngine();
    }

    @Override
    public ScriptEngine getScriptEngine() {
        return scriptEngine;
    }

}
