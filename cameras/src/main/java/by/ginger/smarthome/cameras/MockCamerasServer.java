/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.cameras;

import by.ginger.smarthome.cameras.servlet.MockStreamServlet;
import by.ginger.smarthome.cameras.util.WebResourceUtil;
import java.net.URI;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebXmlConfiguration;

/**
 *
 * @author mirash
 */
public class MockCamerasServer {

    private static final int SERVER_PORT = 8087;
    private static final String WEB_RESOURCE_REF = "img";

    public static Server start(int port) throws Exception {
        Server server = new Server(SERVER_PORT);

        URI webResourceBase = WebResourceUtil.findWebResourceBase(server.getClass().getClassLoader(), WEB_RESOURCE_REF);

        WebAppContext context = new WebAppContext();
        context.setBaseResource(Resource.newResource(webResourceBase));
        context.setConfigurations(new Configuration[]{
            new AnnotationConfiguration(),
            new WebXmlConfiguration()
        });
        context.setContextPath("/");
        context.setParentLoaderPriority(true);

        ServletHolder streamServet = new ServletHolder("stream", MockStreamServlet.class);
        context.addServlet(streamServet, "/stream/*");

        server.setHandler(context);
        server.start();
        server.dump(System.err);
        return server;
    }

    public static void main(String[] args) throws Exception {
        start(SERVER_PORT).start();
    }
}
