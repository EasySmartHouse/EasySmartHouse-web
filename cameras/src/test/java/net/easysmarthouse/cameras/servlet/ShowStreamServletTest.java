/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.cameras.servlet;

import net.easysmarthouse.cameras.ImageTestUtil;
import net.easysmarthouse.cameras.core.CamDeviceReader;
import net.easysmarthouse.cameras.core.WebcamAccessor;
import net.easysmarthouse.cameras.core.WebcamNotAvailableException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author mirash
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(WebcamAccessor.class)
@PowerMockIgnore({"javax.imageio.*", "javax.security.*"})
public class ShowStreamServletTest {

    public ShowStreamServletTest() {
    }
    private ShowStreamServlet streamServlet;

    @Before
    public void setUp() throws MalformedURLException, ServletException {
        PowerMockito.mockStatic(WebcamAccessor.class);
        PowerMockito.when(WebcamAccessor.getCamDeviceReader()).thenReturn(new CamDeviceReader() {
            private BufferedImage cam1;
            private BufferedImage cam2;

            {
                try {
                    cam1 = ImageIO.read(new File("src/test/resources/samples/cam-1.jpg"));
                    cam2 = ImageIO.read(new File("src/test/resources/samples/cam-2.jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public BufferedImage getImage(int deviceNumber)
                    throws WebcamNotAvailableException {
                if (deviceNumber == 0) {
                    return cam1;
                }
                if (deviceNumber == 1) {
                    return cam2;
                }
                throw new WebcamNotAvailableException();
            }
        });

        streamServlet = new ShowStreamServlet();

        ServletContext servletContext = PowerMockito.mock(ServletContext.class);
        PowerMockito.when(servletContext.getResource(Mockito.endsWith("no_cam.png"))).thenReturn(
                new File("src/test/resources/samples/no-cam.jpg").toURI().toURL());

        ServletConfig servletConfig = PowerMockito.mock(ServletConfig.class);
        PowerMockito.when(servletConfig.getServletContext()).thenReturn(servletContext);
        streamServlet.init(servletConfig);
    }

    @Test
    public void init() throws ServletException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("***** init *****");
        streamServlet.init();

        Field noCameraUrlField = ShowStreamServlet.class.getDeclaredField("noCameraUrl");
        noCameraUrlField.setAccessible(true);
        URL noCameraUrl = (URL) noCameraUrlField.get(streamServlet);

        assertNotNull(noCameraUrl);
        assertTrue(noCameraUrl.getFile().endsWith("samples/no-cam.jpg"));
    }

    /**
     * Test of doGet method, of class ShowStreamServlet.
     */
    @Test
    public void testDoGetCam0() throws Exception {
        System.out.println("***** doGetCam0 *****");
        HttpServletRequest request = PowerMockito.mock(HttpServletRequest.class);
        HttpServletResponse response = PowerMockito.mock(HttpServletResponse.class);

        PowerMockito.when(request.getParameter("cam")).thenReturn("0");

        ServletOutputStream mockOutputStream = Mockito.mock(ServletOutputStream.class);
        PowerMockito.when(response.getOutputStream()).thenReturn(mockOutputStream);

        streamServlet.doGet(request, response);
        
        Mockito.verify(response).setContentType(Mockito.eq("image/png"));

        Mockito.verify(mockOutputStream, Mockito.atLeastOnce()).write(
                Mockito.<byte[]>any(), Mockito.anyInt(), Mockito.anyInt());
        Mockito.verify(mockOutputStream, Mockito.atLeastOnce()).flush();
        Mockito.verify(mockOutputStream, Mockito.atLeastOnce()).close();

        StubServletOutputStream outputStream = new StubServletOutputStream();
        PowerMockito.when(response.getOutputStream()).thenReturn(outputStream);

        streamServlet.doGet(request, response);

        InputStream actualImage = new ByteArrayInputStream(outputStream.toByteArray());
        InputStream expectedImage = new FileInputStream("src/test/resources/samples/cam-1.jpg");

        assertTrue(ImageTestUtil.compareImages(actualImage, expectedImage));
    }

    @Test
    public void testDoGetCam1() throws Exception {
        System.out.println("***** doGetCam1 *****");
        HttpServletRequest request = PowerMockito.mock(HttpServletRequest.class);
        HttpServletResponse response = PowerMockito.mock(HttpServletResponse.class);

        PowerMockito.when(request.getParameter("cam")).thenReturn("1");

        ServletOutputStream mockOutputStream = Mockito.mock(ServletOutputStream.class);
        PowerMockito.when(response.getOutputStream()).thenReturn(mockOutputStream);

        streamServlet.doGet(request, response);
        
        Mockito.verify(response).setContentType(Mockito.eq("image/png"));

        Mockito.verify(mockOutputStream, Mockito.atLeastOnce()).write(
                Mockito.<byte[]>any(), Mockito.anyInt(), Mockito.anyInt());
        Mockito.verify(mockOutputStream, Mockito.atLeastOnce()).flush();
        Mockito.verify(mockOutputStream, Mockito.atLeastOnce()).close();

        StubServletOutputStream outputStream = new StubServletOutputStream();
        PowerMockito.when(response.getOutputStream()).thenReturn(outputStream);

        streamServlet.doGet(request, response);

        InputStream actualImage = new ByteArrayInputStream(outputStream.toByteArray());
        InputStream expectedImage = new FileInputStream("src/test/resources/samples/cam-2.jpg");

        assertTrue(ImageTestUtil.compareImages(actualImage, expectedImage));
    }

    @Test
    public void testDoGetCamNotAvailable() throws Exception {
        System.out.println("***** doNotAvailable *****");
        HttpServletRequest request = PowerMockito.mock(HttpServletRequest.class);
        HttpServletResponse response = PowerMockito.mock(HttpServletResponse.class);

        PowerMockito.when(request.getParameter("cam")).thenReturn("45");

        ServletOutputStream mockOutputStream = Mockito.mock(ServletOutputStream.class);
        PowerMockito.when(response.getOutputStream()).thenReturn(mockOutputStream);

        streamServlet.doGet(request, response);

        Mockito.verify(response).setContentType(Mockito.eq("image/png"));
        
        Mockito.verify(mockOutputStream, Mockito.atLeastOnce()).write(
                Mockito.<byte[]>any(), Mockito.anyInt(), Mockito.anyInt());
        Mockito.verify(mockOutputStream, Mockito.atLeastOnce()).flush();
        Mockito.verify(mockOutputStream, Mockito.atLeastOnce()).close();

        StubServletOutputStream outputStream = new StubServletOutputStream();
        PowerMockito.when(response.getOutputStream()).thenReturn(outputStream);

        streamServlet.doGet(request, response);

        InputStream actualImage = new ByteArrayInputStream(outputStream.toByteArray());
        InputStream expectedImage = new FileInputStream("src/test/resources/samples/no-cam.jpg");

        assertTrue(ImageTestUtil.compareImages(actualImage, expectedImage));
    }
}