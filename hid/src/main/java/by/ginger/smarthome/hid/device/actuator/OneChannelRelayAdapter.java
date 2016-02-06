/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.hid.device.actuator;

import by.creepid.jusbrelay.UsbRelayDeviceHandler;
import by.creepid.jusbrelay.UsbRelayDeviceInfo;
import by.creepid.jusbrelay.UsbRelayDeviceType;
import by.creepid.jusbrelay.UsbRelayException;
import by.creepid.jusbrelay.UsbRelayManager;
import by.creepid.jusbrelay.UsbRelayStatus;
import by.creepid.jusbrelay.util.DevicePathHelper;
import by.ginger.smarthome.hid.device.AbstractHidDevice;
import by.ginger.smarthome.provider.device.Closeable;
import by.ginger.smarthome.hid.device.HidDescriptor;
import by.ginger.smarthome.hid.device.RelayDescriptor;
import by.ginger.smarthome.provider.device.DeviceType;
import by.ginger.smarthome.provider.device.actuator.ActuatorType;
import by.ginger.smarthome.provider.device.actuator.SwitchActuator;
import by.ginger.smarthome.provider.device.exception.DeviceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author mirash
 */
public class OneChannelRelayAdapter extends AbstractHidDevice implements SwitchActuator, Closeable {

    private static final Log log = LogFactory.getLog(OneChannelRelayAdapter.class);
    private UsbRelayManager relayManager = RelayManagerHolder.getInstance();
    private final short serial;
    private final UsbRelayDeviceInfo deviceInfo;
    private final UsbRelayDeviceHandler deviceHandler;

    private UsbRelayDeviceInfo findDevice(short serial) {
        try {
            UsbRelayDeviceInfo[] infos = relayManager.deviceEnumerate();
            for (UsbRelayDeviceInfo usbRelayDeviceInfo : infos) {
                if (usbRelayDeviceInfo.getDeviceType() != UsbRelayDeviceType.ONE_CHANNEL) {
                    continue;
                }

                String path = usbRelayDeviceInfo.getDevicePath();
                if (DevicePathHelper.getSerial(path) == serial) {
                    return usbRelayDeviceInfo;
                }
            }
        } catch (Exception ex) {
            throw new IllegalStateException("Error while read usb relay devices list", ex);
        }
        throw new IllegalStateException("Cannot find usb relay");
    }

    public OneChannelRelayAdapter(byte portNumber, short serial) {
        super(portNumber);
        this.serial = serial;
        this.deviceInfo = findDevice(serial);

        try {
            this.deviceHandler = relayManager.deviceOpen(deviceInfo.getSerialNumber());
        } catch (UsbRelayException ex) {
            throw new IllegalStateException("Cannot open relay: " + serial, ex);
        }
    }

    @Override
    public int getSerialNumber() {
        return serial;
    }

    @Override
    public HidDescriptor getDescriptor() {
        return RelayDescriptor.getInstance();
    }

    @Override
    public String getLabel() {
        return "One channel USB relay";
    }

    @Override
    public String getDescription() {
        return getLabel() + " " + getAddress();
    }

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Actuator;
    }

    @Override
    public Boolean getState() throws DeviceException {
        try {
            UsbRelayStatus status = relayManager.getStatus(deviceInfo.getSerialNumber(), deviceHandler)[0];
            return status.isOpen();
        } catch (UsbRelayException ex) {
            log.error(ex);
            return null;
        }
    }

    @Override
    public void setState(Boolean state) throws DeviceException {
        try {
            if (state) {
                relayManager.openRelayChannel(deviceHandler, 0);
            } else {
                relayManager.closeRelayChannel(deviceHandler, 0);
            }
        } catch (UsbRelayException ex) {
            log.error("Cannot change relay state", ex);
        }
    }

    @Override
    public ActuatorType getActuatorType() {
        return ActuatorType.switchActuator;
    }

    @Override
    public void close() {
        if (deviceHandler != null) {
            try {
                relayManager.closeRelay(deviceHandler);
            } catch (UsbRelayException ex) {
                log.error(ex);
            }
        }
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
