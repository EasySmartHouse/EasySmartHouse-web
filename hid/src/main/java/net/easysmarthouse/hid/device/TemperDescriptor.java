/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.hid.device;

import net.easysmarthouse.hid.device.natives.Temper;

/**
 *
 * @author rusakovich
 */
public class TemperDescriptor extends HidDescriptor {

    private static TemperDescriptor desc = null;

    protected TemperDescriptor() {
        super(Temper.PRODUCT_ID, Temper.VENDOR_ID);
    }

    public static HidDescriptor getInstance() {
        if (desc == null) {
            desc = new TemperDescriptor();
        }
        return desc;
    }

}
