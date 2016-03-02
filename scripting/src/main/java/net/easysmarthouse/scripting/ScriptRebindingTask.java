/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting;

import java.util.TimerTask;
import net.easysmarthouse.scripting.device.ScriptableDevice;

/**
 *
 * @author rusakovich
 */
public class ScriptRebindingTask extends TimerTask {

    private final ScriptableDevice scriptableDevice;
    private final ScriptSource scriptSource;

    public ScriptRebindingTask(ScriptableDevice scriptableDevice, ScriptSource scriptSource) {
        this.scriptableDevice = scriptableDevice;
        this.scriptSource = scriptSource;
    }

    @Override
    public void run() {
        synchronized (scriptableDevice) {            
            scriptableDevice.unbind();
            scriptableDevice.bind(scriptSource);
        }
    }

}
