/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.cameras.core;

import java.awt.image.BufferedImage;
/**
 *
 * @author mirash
 */
public interface CamDeviceReader {

    public BufferedImage getImage(int deviceNumber) throws WebcamNotAvailableException;
    
}
