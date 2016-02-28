/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.serial.device;

/**
 *
 * @author mirash
 */
public abstract class AbstractSerialDevice implements SerialDevice {

    protected final byte portNumber;
    protected String address;

    public AbstractSerialDevice(byte portNumber) {
        this.portNumber = portNumber;
        buildAddress();
    }

    private void buildAddress() {
        address = "COM" + portNumber;
    }

    protected final String getPortStr() {
        return address;
    }

    @Override
    public String getAddress() {
        return address;
    }
    

}
