/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.cameras.core;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamUpdater;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rusakovich
 */
public class WebcamImageObservable implements WebcamUpdater.DelayCalculator, WebcamListener {

    private static final long IMAGE_UPDATE_DELAY = 1000;

    private Map<String, Webcam> webcams = new HashMap<String, Webcam>();

    private List<WebcamImageObserver> imageObservers = new ArrayList<WebcamImageObserver>();

    private static final WebcamImageObservable CACHE = new WebcamImageObservable();

    public WebcamImageObservable() {
        for (Webcam webcam : Webcam.getWebcams()) {
            webcam.addWebcamListener(this);
            webcam.open(true, this);
            webcams.put(webcam.getName(), webcam);
        }
    }

    @Override
    public long calculateDelay(long snapshotDuration, double deviceFps) {
        return Math.max(IMAGE_UPDATE_DELAY - snapshotDuration, 0);
    }

    public static BufferedImage getImage(String name) {
        return CACHE.webcams.get(name).getImage();
    }

    public static List<String> getWebcamNames() {
        return new ArrayList<String>(CACHE.webcams.keySet());
    }

    @Override
    public void webcamOpen(WebcamEvent we) {
    }

    @Override
    public void webcamClosed(WebcamEvent we) {
    }

    @Override
    public void webcamDisposed(WebcamEvent we) {
    }

    @Override
    public void webcamImageObtained(WebcamEvent we) {
        for (WebcamImageObserver observer : imageObservers) {
            observer.newImage(we.getSource(), we.getImage());
        }
    }

    public static void subscribe(WebcamImageObserver observer) {
        CACHE.imageObservers.add(observer);
    }

    public static void unsubscribe(WebcamImageObserver observer) {
        CACHE.imageObservers.remove(observer);
    }
}
