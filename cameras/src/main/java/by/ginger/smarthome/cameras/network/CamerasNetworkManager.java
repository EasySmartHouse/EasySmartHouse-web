/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.cameras.network;

import by.ginger.smarthome.cameras.core.WebcamUsbDeviceFilter;
import by.ginger.smarthome.cameras.device.alarm.MotionDetectorElement;
import by.ginger.smarthome.network.AbstractStorableNetworkManager;
import by.ginger.smarthome.network.exception.NetworkException;
import by.ginger.smarthome.network.extension.ConversionExtension;
import by.ginger.smarthome.network.extension.IdleConversionExtension;
import by.ginger.smarthome.network.util.AddressHelper;
import by.ginger.smarthome.provider.device.Closeable;
import by.ginger.smarthome.provider.device.Device;
import com.github.sarxos.webcam.Webcam;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author mirash
 */
public class CamerasNetworkManager extends AbstractStorableNetworkManager {

    private List<String> managedDevices = Collections.EMPTY_LIST;

    @Override
    public void init() {
        List<Webcam> cams = WebcamUsbDeviceFilter.getWebcams();
        for (Webcam webcam : cams) {
            synchronized (webcam) {
                if (managedDevices.contains(webcam.getName())) {
                    devices.add(new MotionDetectorElement(webcam));
                }
            }
        }
    }

    @Override
    public void destroy() {
        for (Device device : devices) {
            if (device instanceof Closeable) {
                Closeable closeable = (Closeable) device;
                closeable.close();
            }
        }
    }

    @Override
    public void startSession() throws NetworkException {
    }

    @Override
    public void endSession() throws NetworkException {
    }

    @Override
    public void refreshDevices() throws NetworkException {
    }

    @Override
    public List<Device> getDevices() throws NetworkException {
        return devices;
    }

    @Override
    public List<Long> getDevicesAddresses() throws NetworkException {
        List<Long> addresses = new ArrayList<>();
        for (Device device : devices) {
            long addr = AddressHelper.buidldHash(device.getAddress(), 13l);
            addresses.add(addr);
        }
        return addresses;
    }

    @Override
    public boolean isDevicePresent(String address) throws NetworkException {
        for (Device device : devices) {
            if (device.getAddress().equalsIgnoreCase(address)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ConversionExtension getConversionExtension() {
        return new IdleConversionExtension();
    }

    public void setManagedCameraDevices(List<String> managedDevices) {
        this.managedDevices = managedDevices;
    }
}
