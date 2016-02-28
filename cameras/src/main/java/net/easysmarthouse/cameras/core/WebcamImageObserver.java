/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.cameras.core;

import com.github.sarxos.webcam.Webcam;
import java.awt.image.BufferedImage;

/**
 *
 * @author rusakovich
 */
public interface WebcamImageObserver {

    public void newImage(Webcam webcam, BufferedImage image);

}
