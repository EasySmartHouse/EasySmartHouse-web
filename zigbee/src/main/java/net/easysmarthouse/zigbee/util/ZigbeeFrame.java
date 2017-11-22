/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.zigbee.util;

import java.io.Serializable;
import java.util.Arrays;
import net.easysmarthouse.serial.util.NumberUtil;

/**
 *
 * @author rusakovich
 *
 * tvsaDat[1-8] = <extended IEEE address>
 * tvsaDat[9] = <parent address LSB>
 * tvsaDat[10] = <parent address MSB>
 * tvsaDat[11] = <temperature data>
 * tvsaDat[12] = <voltage data>
 * tvsaDat[13] = <0x80 if router or non-dongle coordinator, otherwise for an end
 * device it is defined as follows> tvsaDat[13] &= (0xFF ^ 0x80); tvsaDat[14] =
 * 0x01 if using source routing (i.e. if TVSA_SRC_RTG), otherwise is not
 * explicitly defined tvsaDat[15] = CNF error count
 *
 * FE //This is a start of frame delimiter 0A //10 46 //This takes the lower 8
 * bits 0x8746 87 // This takes the higher 8 bits 0x8746 70 //Lower 8 bits of
 * the source address 79 // Upper 8 bits of the source address 02 //Lower 8 bits
 * of "2" 00 //Upper 8 bits of "2" 04 //Lower 8 bits of "4" 00 //Upper 8 bits of
 * "4" 13 //temperature data (19C) 1D //voltage data 00 //LSB of the parent
 * devices address 00 //MSP of the parent devices address CA //FCS Calculation
 * on the previous 13 bytes (i.e. Packet[1] to Packet[13])
 */
public class ZigbeeFrame implements Serializable {

    public static final int FRAME_SIZE = 15;

    public final byte[] frameData;

    public ZigbeeFrame(byte[] frameData) {
        this.frameData = frameData;
    }

    public String getFrameStart() {
        return NumberUtil.toHexStr(Arrays.copyOfRange(frameData, 0, 4));
    }

    public String getSourceAddress() {
        byte[] addrBytes = {frameData[5], frameData[4]};
        return NumberUtil.toHexStr(addrBytes);
    }

    public byte getTemperatureData() {
        return frameData[10];
    }

    public byte getVoltageData() {
        return frameData[11];
    }

    public String getParentDevicesAddress() {
        byte[] addrBytes = {frameData[13], frameData[12]};
        return NumberUtil.toHexStr(addrBytes);
    }

    public byte getFCSCalculation() {
        return frameData[14];
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Arrays.hashCode(this.frameData);
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
        final ZigbeeFrame other = (ZigbeeFrame) obj;
        if (!Arrays.equals(this.frameData, other.frameData)) {
            return false;
        }
        return true;
    }

}
