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
public enum ScriptType {
    JavaScript("js"),
    Groovy("groovy"),
    JRuby("rb");

    private final String ext;

    private ScriptType(String ext) {
        this.ext = ext;
    }

    public String getExtension() {
        return ext;
    }

    public static ScriptType getScriptTypeByExt(String ext) {
        if (ext == null) {
            return null;
        }

        for (ScriptType type : ScriptType.values()) {
            if (type.getExtension().equalsIgnoreCase(ext)) {
                return type;
            }
        }

        return null;
    }

}
