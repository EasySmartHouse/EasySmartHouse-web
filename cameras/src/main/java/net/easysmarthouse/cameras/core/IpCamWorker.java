/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.cameras.core;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamStorage;
import java.io.File;
import java.io.PrintStream;

/**
 *
 * @author rusakovich
 */
public class IpCamWorker {

    private IpCamWorker() {
    }

    public static interface IpCamPrinter {

        public PrintStream getPrintStream();
    }

    public static void init(File config) {
        IpCamStorage storage = new IpCamStorage(config);
        Webcam.setDriver(new IpCamDriver(storage));
    }

    public static void printCams(IpCamPrinter printer) {
        for (String name : WebcamImageObservable.getWebcamNames()) {
            printer.getPrintStream().println("IP cam available [" + name + "]");
        }
    }

}
