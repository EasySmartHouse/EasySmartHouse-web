/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.cameras;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author mirash
 */
public class ImageTestUtil {

    private ImageTestUtil() {
    }

    public static boolean compareImages(InputStream oneImage, InputStream anotherImage) {
        try {
            BufferedImage buffImageOne = ImageIO.read(oneImage);
            DataBuffer dataBuffOne = buffImageOne.getData().getDataBuffer();
            int sizeOne = dataBuffOne.getSize();
            
            BufferedImage biffImageAnother = ImageIO.read(anotherImage);
            DataBuffer dataBuffAnother = biffImageAnother.getData().getDataBuffer();
            int sizeAnother = dataBuffAnother.getSize();

            if (sizeOne == sizeAnother) {
                for (int i = 0; i < sizeOne; i++) {
                    if (dataBuffOne.getElem(i) != dataBuffAnother.getElem(i)) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
