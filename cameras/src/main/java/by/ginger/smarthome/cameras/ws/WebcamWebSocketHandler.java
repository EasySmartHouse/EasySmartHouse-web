/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.cameras.ws;

import by.ginger.smarthome.cameras.core.WebcamImageObservable;
import by.ginger.smarthome.cameras.core.WebcamImageObserver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sarxos.webcam.Webcam;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

/**
 *
 * @author rusakovich
 */
@WebSocket
public class WebcamWebSocketHandler implements WebcamImageObserver {

    private static final Logger log = Logger.getLogger(WebcamWebSocketHandler.class.getName());

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Session session;

    private void close() {
        try {
            if (session != null) {
                session.close();
            }
            session = null;
        } finally {
            WebcamImageObservable.unsubscribe(this);
        }
    }

    private void send(String message) {
        try {
            session.getRemote().sendString(message);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception when sending string", e);
        }
    }

    private void send(Object object) {
        try {
            send(MAPPER.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            log.log(Level.SEVERE, null, e);
        }
    }

    private void start(Session session) {
        this.session = session;

        Map<String, Object> message = new HashMap<String, Object>();
        message.put("type", "list");
        message.put("webcams", WebcamImageObservable.getWebcamNames());

        send(message);

        WebcamImageObservable.subscribe(this);
    }

    @Override
    public void newImage(Webcam webcam, BufferedImage image) {
        log.log(Level.INFO, "New image from camera [{0}]",
                new Object[]{webcam.getDevice().getName()});

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "JPG", baos);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Error while getting the image", e);
        }

        String base64 = null;
        try {
            base64 = new String(Base64.encodeBase64(baos.toByteArray()), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.log(Level.SEVERE, "Error while encoding", e);
        }

        Map<String, Object> message = new HashMap<String, Object>();
        message.put("type", "image");
        message.put("webcam", webcam.getName());
        message.put("image", base64);

        send(message);
    }

    @OnWebSocketClose
    public void onClose(int status, String reason) {
        log.log(Level.INFO, "WebSocket closed, status = [{0}], reason = [{1}]",
                new Object[]{status, reason});
        close();
    }

    @OnWebSocketError
    public void onError(Throwable t) {
        log.log(Level.SEVERE, "WebSocket error", t);
        close();
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        log.log(Level.INFO, "WebSocket connect, from = [{0}]",
                new Object[]{session.getRemoteAddress().getAddress()});
        start(session);
    }

    @OnWebSocketMessage
    public void onMessage(String message) {
        log.log(Level.INFO, "WebSocket message, text = [{0}]", new Object[]{message});
    }

}
