/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.mocks.device.util;

import java.util.Random;

/**
 *
 * @author mirash
 */
public class ValueHelper {

    private ValueHelper() {
    }

    public static double getRandom(double minEdge, double maxEdge) {
        Random random = new Random();
        return minEdge + (maxEdge - minEdge) * random.nextDouble();
    }
}
