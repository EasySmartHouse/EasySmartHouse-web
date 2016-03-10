/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting;

import java.io.File;
import java.io.FilenameFilter;
import net.easysmarthouse.scripting.util.FileHelper;

/**
 *
 * @author rusakovich
 */
public class ScriptFileFilter implements FilenameFilter {

    @Override
    public boolean accept(File dir, String name) {
        String ext = FileHelper.getExtension(name);
        return ScriptType.getScriptTypeByExt(ext) != null;
    }

}
