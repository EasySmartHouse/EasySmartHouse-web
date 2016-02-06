/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.hid.device;

/**
 *
 * @author mirash
 */
public class HidAddressHelper {
    
    private HidAddressHelper(){
    }

    public static Long getLong(AbstractHidDevice device) {
        String str = "" + device.getPortNumber()
                + device.getDescriptor().getProductId()
                + device.getDescriptor().getVendorId()
                + device.getSerialNumber();
        return Long.valueOf(str);
    }
    
}
