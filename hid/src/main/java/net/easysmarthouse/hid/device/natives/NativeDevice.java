/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.hid.device.natives;

import net.easysmarthouse.hid.device.natives.util.DllManager;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author mirash
 */
abstract class NativeDevice {

    // Code for loading the windows native dll
    static {
        try {
            File libFile = DllManager.findLibFile();
            System.load(libFile.getAbsolutePath());
            DllManager.cleanupTempFiles();
        } catch (IOException e) {
            throw new RuntimeException("Error loading dll" + e.getMessage(), e);
        }
    }
}
