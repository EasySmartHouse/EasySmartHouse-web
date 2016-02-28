/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.cameras;

import net.easysmarthouse.cameras.core.IpCamWorker;
import net.easysmarthouse.cameras.core.IpCamWorker.IpCamPrinter;
import net.easysmarthouse.cameras.ws.WebcamWebSocketHandler;
import java.io.File;
import java.io.PrintStream;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 *
 * @author rusakovich
 */
public class CamerasProxyServer {

    private static final int CAM_PROXY_SERVER_PORT = 8123;
    private static final String IP_CAM_CONFIG = "src/main/resources/cameras.xml";

    public static Server start(int port, File config) throws Exception {
        IpCamWorker.init(config);
        IpCamWorker.printCams(new IpCamPrinter() {

            @Override
            public PrintStream getPrintStream() {
                return System.out;
            }
        });

        Server server = new Server(port);
        WebSocketHandler wsHandler = new WebSocketHandler() {

            @Override
            public void configure(WebSocketServletFactory factory) {
                factory.register(WebcamWebSocketHandler.class);
            }
        };

        server.setHandler(wsHandler);
        server.start();
        return server;
    }

    public static void main(String[] args) throws Exception {
        start(CAM_PROXY_SERVER_PORT, new File(IP_CAM_CONFIG)).join();
    }
}
