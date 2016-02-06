/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.hid.device.natives;

import org.junit.Test;
import static junit.framework.Assert.assertTrue;

/**
 *
 * @author rusakovich
 */
public class TemperIntegrationTest {

    private static final int ITERATIONS = 100;

    @Test
    public void testReadTemperature() throws NativeDeviceException {
        System.out.println("**** testReadTemperature ****");
        Temper temper = new Temper((short) 0);
        try {

            for (int i = 0; i < ITERATIONS; i++) {
                double temp = temper.readTemperature();
                System.out.println(temp);
                assertTrue(temp > 0.0);
            }

        } finally {
            if (temper != null) {
                temper.close();
            }
        }
    }
}
