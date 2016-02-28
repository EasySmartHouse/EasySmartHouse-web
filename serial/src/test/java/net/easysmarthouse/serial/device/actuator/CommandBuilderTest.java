/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.serial.device.actuator;

import net.easysmarthouse.serial.util.NumberUtil;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mirash
 */
public class CommandBuilderTest {

    /**
     * Test of getCommand method, of class CmdBuilder.
     */
    @Test
    public void testGetControlCommand() {
        System.out.println("**** getControlCommand ***");
        CommandBuilder instance = CommandBuilder.INSTANCE_4CH;

        ControlCommand purpose = ControlCommand.READING_STATUS;
        int channel = 0;

        byte[] result = instance.getControlCommand(purpose, channel);
        assertEquals("55560000000000AB", NumberUtil.toHexStr(result));

        purpose = ControlCommand.OPEN;
        channel = 1;
        result = instance.getControlCommand(purpose, channel);
        assertEquals("55560000000201AE", NumberUtil.toHexStr(result));

        purpose = ControlCommand.CLOSE;
        channel = 0;
        result = instance.getControlCommand(purpose, channel);
        assertEquals("55560000000102AE", NumberUtil.toHexStr(result));

        purpose = ControlCommand.TOGGLE;
        channel = 1;
        result = instance.getControlCommand(purpose, channel);
        assertEquals("55560000000203B0", NumberUtil.toHexStr(result));
    }
}