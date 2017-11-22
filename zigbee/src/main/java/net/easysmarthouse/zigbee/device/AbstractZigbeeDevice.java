/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.zigbee.device;

/**
 *
 * @author rusakovich
 */
public abstract class AbstractZigbeeDevice implements ZigbeeDevice {

    private final String address;

    private int addressHi;
    private int addressLo;

    private void setAddressHiLo(String address) {
        int addressDec = Integer.decode(address);
        this.addressHi = addressDec >> 8;
        this.addressLo = addressDec & 0x00FF;
    }

    public AbstractZigbeeDevice(String address) {
        if (address == null) {
            throw new IllegalArgumentException("Please, specify device address");
        }

        if (address.length() != 4) {
            throw new IllegalArgumentException("Please, specify correct device address (16-bit)");
        }

        this.address = address;
        setAddressHiLo(address);
    }

    public int getAddressHi() {
        return addressHi;
    }

    public int getAddressLo() {
        return addressLo;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.addressHi;
        hash = 43 * hash + this.addressLo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractZigbeeDevice other = (AbstractZigbeeDevice) obj;
        if (this.addressHi != other.addressHi) {
            return false;
        }
        if (this.addressLo != other.addressLo) {
            return false;
        }
        return true;
    }

}
