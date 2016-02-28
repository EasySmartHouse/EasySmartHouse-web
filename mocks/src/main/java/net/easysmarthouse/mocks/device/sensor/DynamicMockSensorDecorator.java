/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.mocks.device.sensor;

import net.easysmarthouse.mocks.device.util.ValueHelper;
import net.easysmarthouse.provider.device.exception.DeviceException;
import java.util.Random;

/**
 *
 * @author mirash
 */
public class DynamicMockSensorDecorator extends MockSensorDecorator {

    private static final long DEFAULT_CHANGE_DELAY = 1000;
    private double minEdge;
    private double maxEdge;
    public volatile double value;
    public long changeDelay = DEFAULT_CHANGE_DELAY;

    private void startChanging() {
        Thread changingThread = new Thread(
                new Runnable() {
            @Override
            public void run() {
                for (;;) {
                    value = ValueHelper.getRandom(minEdge, maxEdge);

                    try {
                        Thread.sleep(changeDelay);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        changingThread.setDaemon(true);
        changingThread.start();
    }

    public DynamicMockSensorDecorator(MockSensor mockSensor) {
        super(mockSensor);
    }

    public void setRange(double minEdge, double maxEdge) {
        this.minEdge = minEdge;
        this.maxEdge = maxEdge;
        this.startChanging();
    }

    @Override
    public double getValue() throws DeviceException {
        return value;
    }

    public void setChangeDelay(long changeDelay) {
        this.changeDelay = changeDelay;
    }
}
