/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.hid.device;

/**
 *
 * @author mirash
 */
public class RelayDescriptor extends HidDescriptor {

    static final int RELAY_VID = 0x16c0;
    static final int RELAY_PID = 0x05df;
    private static HidDescriptor desc = null;

    protected RelayDescriptor() {
            super(RELAY_PID, RELAY_VID);
    }

    public static HidDescriptor getInstance() {
        if (desc == null) {
            desc = new RelayDescriptor();
        }
        return desc;
    }
}
