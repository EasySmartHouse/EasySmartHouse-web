/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.hid.device;

/**
 *
 * @author mirash
 */
public abstract class AbstractHidDevice implements HidDevice {

    protected final byte portNumber;
    private String hidAddress;

    public AbstractHidDevice(byte portNumber) {
        this.portNumber = portNumber;
        buildAddress();
    }

    private void buildAddress() {
        hidAddress = "USB" + portNumber
                + ":" + getDescriptor().toString()
                + ":" + getSerialNumber();
    }

    @Override
    public final String getAddress() {
        return hidAddress;
    }

    public abstract int getSerialNumber();

    public abstract HidDescriptor getDescriptor();

    public byte getPortNumber() {
        return portNumber;
    }

    public String getHidAddress() {
        return hidAddress;
    }
}
