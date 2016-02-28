/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.mocks.network;

import net.easysmarthouse.network.extension.ConversionExtension;
import net.easysmarthouse.provider.device.Device;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author mirash
 */
public class MockNetworkManagerMultiThreadTest {

    private static final MockNetworkManager instance = new MockNetworkManager();
    private ThreadGroup networkGroup = new ThreadGroup("Network group");

    private interface NetworkLogic {

        public void execute() throws Exception;
    }

    private class NetworkWorker extends Thread {

        private NetworkLogic logic;

        public NetworkWorker(NetworkLogic logic, String workerName, ThreadGroup workerGroup) {
            super(workerGroup, workerName);
            this.logic = logic;
        }

        public NetworkWorker(NetworkLogic logic) {
            super();
            this.logic = logic;
        }

        @Override
        public void run() {
            try {
                logic.execute();
            } catch (Exception ex) {
                String threadName = Thread.currentThread().getName();
                fail(threadName + ": " + ex.getMessage());
            }
        }
    }

    @BeforeClass
    public static void setUpClass() {
        List<Device> devices = MockNetworkHelper.createSimpleDevicesModel();
        instance.setDevices(devices);
    }

    @Test(timeout = 1600)
    public void testMultiThread() throws Exception {
        System.out.println("**** multiThread *****");

        final NetworkLogic rightLogic = new NetworkLogic() {
            private static final long WORK_DELAY = 500;

            @Override
            public void execute() throws Exception {
                try {
                    instance.startSession();
                    Thread.sleep(WORK_DELAY);
                    List result = instance.getDevices();
                    assertNotNull(result);
                    assertEquals(5, result.size());

                    result = instance.getDevicesAddresses();
                    assertNotNull(result);
                    assertEquals(5, result.size());

                    ConversionExtension ext = instance.getConversionExtension();
                    assertNotNull(ext);
                } finally {
                    instance.endSession();
                }
            }
        };

        final NetworkLogic illegalLogic = new NetworkLogic() {
            private static final long WORK_DELAY = 400;

            @Override
            public void execute() throws Exception {
                try {
                    Thread.sleep(WORK_DELAY);
                    instance.getDevices();
                    fail("IllegalStateException must be thrown");
                } catch (Throwable thr) {
                    assertTrue(thr instanceof IllegalStateException);
                } finally {
                    instance.endSession();
                }
            }
        };

        NetworkWorker worker1 = new NetworkWorker(rightLogic, "worker1", networkGroup);
        NetworkWorker worker2 = new NetworkWorker(rightLogic, "worker2", networkGroup);
        NetworkWorker worker3 = new NetworkWorker(illegalLogic, "worker3", networkGroup);
        NetworkWorker worker4 = new NetworkWorker(rightLogic, "worker4", networkGroup);

        worker1.start();
        worker2.start();
        worker3.start();
        worker4.start();

        try {
            worker1.join();
            worker2.join();
            worker3.join();
            worker4.join();
        } catch (Exception e) {
            fail(e.getMessage());
        }



    }
}
