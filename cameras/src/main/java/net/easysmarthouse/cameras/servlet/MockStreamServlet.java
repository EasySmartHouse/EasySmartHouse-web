/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.cameras.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mirash
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/stream"})
public class MockStreamServlet extends HttpServlet {

    private static final int CAM_NUMBER_DEFAULT = 0;
    private static final String CAM_PARAM = "cam";
    private URL outdoorCameraUrl;
    private URL livingRoomCameraUrl;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = this.getServletContext();
        try {
            this.outdoorCameraUrl = context.getResource("/cam-outdoor-sample.jpg");
            this.livingRoomCameraUrl = context.getResource("/cam-living-room-sample.jpg");
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpg");

        String camParam = request.getParameter(CAM_PARAM);
        int camNum = (camParam != null && !camParam.isEmpty())
                ? Integer.parseInt(camParam) : CAM_NUMBER_DEFAULT;

        URL imageUrl = (camNum == 0) ? outdoorCameraUrl : livingRoomCameraUrl;
        BufferedImage bi = ImageIO.read(imageUrl);

        try (OutputStream out = response.getOutputStream()) {
            ImageIO.write(bi, "jpg", out);
            out.flush();
            out.close();
        }

    }
}