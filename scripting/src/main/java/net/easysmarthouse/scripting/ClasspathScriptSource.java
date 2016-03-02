/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting;

import java.io.IOException;
import java.io.InputStream;
import net.easysmarthouse.scripting.util.FileHelper;

/**
 *
 * @author rusakovich
 */
class ClasspathScriptSource extends BaseScriptSource {

    public ClasspathScriptSource(String filePath) {
        super(filePath);
    }

    @Override
    public String getScript() throws ScriptException {
        try {
            InputStream is = this.getClass().getClassLoader()
                    .getResourceAsStream(filePath);

            return FileHelper.readFully(is, "UTF-8");
        } catch (IOException ex) {
            throw new ScriptException(ex);
        }
    }

}
