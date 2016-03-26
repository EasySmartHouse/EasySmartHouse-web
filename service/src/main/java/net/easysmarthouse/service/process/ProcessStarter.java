/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.service.process;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author nightingale
 */
public final class ProcessStarter {

    private static final String javaHome = System.getProperty("java.home");
    private static final String javaBin = javaHome
            + File.separator + "bin"
            + File.separator + "java";

    private ProcessStarter() {
    }

    public static Process exec(String className, String classpath) throws IOException,
            InterruptedException {

        ProcessBuilder builder = new ProcessBuilder(
                javaBin, "-cp", classpath, className);

        return builder.start();
    }

}
