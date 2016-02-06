/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.cameras.servlet;

import by.ginger.smarthome.cameras.core.CamDeviceReader;
import by.ginger.smarthome.cameras.core.WebcamAccessor;
import by.ginger.smarthome.cameras.core.WebcamNotAvailableException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rusakovich
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/stream"})
public class ShowStreamServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(ShowStreamServlet.class.getName());
    private static final int CAM_NUMBER_DEFAULT = 0;
    private static final String CAM_PARAM = "cam";
    private URL noCameraUrl;
    private final CamDeviceReader camReader = WebcamAccessor.getCamDeviceReader();

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = this.getServletContext();
        try {
            this.noCameraUrl = context.getResource("/no_cam.png");
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/png");

        String camParam = request.getParameter(CAM_PARAM);
        int camNum = (camParam != null && !camParam.isEmpty())
                ? Integer.parseInt(camParam) : CAM_NUMBER_DEFAULT;

        BufferedImage bi;
        try {
            bi = camReader.getImage(camNum);
        } catch (WebcamNotAvailableException ex) {
            log.log(Level.SEVERE, "Webcam not available [" + camNum + "]", ex);
            bi = ImageIO.read(noCameraUrl);
        }

        try (OutputStream out = response.getOutputStream()) {
            ImageIO.write(bi, "png", out);
            out.flush();
            out.close();
        }

    }
}
