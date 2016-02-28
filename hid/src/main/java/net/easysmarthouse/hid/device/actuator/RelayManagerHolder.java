/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.hid.device.actuator;

import by.creepid.jusbrelay.NativeUsbRelayManager;
import by.creepid.jusbrelay.UsbRelayException;
import by.creepid.jusbrelay.UsbRelayManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author mirash
 */
class RelayManagerHolder {

    private static final Log log = LogFactory.getLog(RelayManagerHolder.class);

    private static class InstanceHolder {

        private static final UsbRelayManager MANAGER = NativeUsbRelayManager.getInstance();
        private static final RelayManagerHolder HOLDER_INSTANCE = new RelayManagerHolder();

        static {
            try {
                MANAGER.relayInit();
            } catch (UsbRelayException ex) {
                log.error("Cannot init USB relay manager", ex);
            }
        }
    }

    private RelayManagerHolder() {
    }

    public static UsbRelayManager getInstance() {
        return InstanceHolder.MANAGER;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

        UsbRelayManager manager = getInstance();
        if (manager != null) {
            manager.relayExit();
        }

    }
}
