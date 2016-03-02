/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting;

import net.easysmarthouse.scripting.util.FileHelper;

/**
 *
 * @author rusakovich
 */
class FileScriptSource extends BaseScriptSource {

    public FileScriptSource(String filePath) {
        super(filePath);
    }

    @Override
    public String getScript() throws ScriptException {
        return FileHelper.readFile(filePath);
    }

}
