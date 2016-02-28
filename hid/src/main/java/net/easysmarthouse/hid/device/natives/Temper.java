/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.hid.device.natives;


/**
 *
 * @author mirash
 */
public class Temper extends NativeDevice {

    public static final int VENDOR_ID = 0x0c45;
    public static final int PRODUCT_ID = 0x7401;

    public Temper(short serialNumber) throws NativeDeviceException {
        init(serialNumber);
    }

    private native int init(short serialNumber) throws NativeDeviceException;

    public native double readTemperature() throws NativeDeviceException;

    public native void close() throws NativeDeviceException;
}
