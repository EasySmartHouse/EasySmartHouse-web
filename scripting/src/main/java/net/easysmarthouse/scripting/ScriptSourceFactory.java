/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting;

/**
 *
 * @author rusakovich
 */
public class ScriptSourceFactory {

    private ScriptSourceFactory() {
    }

    public static ScriptSource createScriptResource(String path) {
        if (path == null) {
            return new NullScriptSource();
        }

        if (path.startsWith("file:")) {
            return new FileScriptSource(path.split(":")[1]);
        }

        if (path.startsWith("classpath:")) {
            return new ClasspathScriptSource(path.split(":")[1]);
        }

        return new NullScriptSource();
    }

}
