/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.mocks.device.key;

import net.easysmarthouse.mocks.device.DeviceObservable;
import net.easysmarthouse.provider.device.Device;

/**
 *
 * @author mirash
 */
public class KeyMockDeviceAutoremovable extends KeyMockDevice {

    private static final long DEFAULT_REMOVE_DELAY = 30000;
    public long removeDelay = DEFAULT_REMOVE_DELAY;
    private final DeviceObservable deviceObservable;

    public KeyMockDeviceAutoremovable(DeviceObservable deviceObservable) {
        this.deviceObservable = deviceObservable;
        startAutoremove();
    }

    private void startAutoremove() {
        final Device _this = this;

        Thread changingThread = new Thread(
                new Runnable() {
            private void someDelay() {
                try {
                    Thread.sleep(removeDelay);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void run() {
                for (;;) {
                    deviceObservable.addDevice(_this);
                    someDelay();
                    deviceObservable.removeDevice(_this);
                    someDelay();

                }
            }
        });
        changingThread.setDaemon(true);
        changingThread.start();
    }

    public void setRemoveDelay(long removeDelay) {
        this.removeDelay = removeDelay;
    }
}
