/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.serial.device.actuator;

/**
 *
 * @author mirash
 */
public interface CommandBuilder {

    public static final byte[] READING_STATE_RESPONSE = {0x33, 0x3C, 0x00, 0x00, 0x00, 0x04, 0x02, 0x75};

    public byte[] getControlCommand(ControlCommand purpose, int channel);

    public byte[] getReturnCommand(CommandReturn response, int channel);
    public static final CommandBuilder INSTANCE_4CH = new CommandBuilder() {
        @Override
        public byte[] getControlCommand(ControlCommand purpose, int channel) {
            if (purpose == null) {
                throw new IllegalArgumentException("Command purpose must be set");
            }

            if (channel < 0 || channel > 3) {
                throw new IllegalArgumentException("Channel must be between 0 and 3");
            }

            byte[] cmd = new byte[8];

            //Frame head
            cmd[0] = 0x55;
            cmd[1] = 0x56;

            //Reserved bytes
            cmd[2] = 0x00;
            cmd[3] = 0x00;
            cmd[4] = 0x00;

            if (purpose != ControlCommand.READING_STATUS) {
                switch (channel) {
                    case 0:
                        cmd[5] = 0x01;
                        break;
                    case 1:
                        cmd[5] = 0x02;
                        break;
                    case 2:
                        cmd[5] = 0x03;
                        break;
                    case 3:
                        cmd[5] = 0x04;
                        break;
                }
            } else {
                cmd[5] = 0x00;
            }

            switch (purpose) {
                case READING_STATUS:
                    //Command
                    cmd[6] = 0x00;
                    //checksum
                    cmd[7] = (byte) 0xAB;
                    break;
                case OPEN:
                    //Command
                    cmd[6] = 0x01;
                    //checksum
                    switch (channel) {
                        case 0:
                            cmd[7] = (byte) 0xAD;
                            break;
                        case 1:
                            cmd[7] = (byte) 0xAE;
                            break;
                        case 2:
                            cmd[7] = (byte) 0xAF;
                            break;
                        case 3:
                            cmd[7] = (byte) 0xB0;
                            break;
                    }
                    break;
                case CLOSE:
                    //Command
                    cmd[6] = 0x02;
                    //checksum
                    switch (channel) {
                        case 0:
                            cmd[7] = (byte) 0xAE;
                            break;
                        case 1:
                            cmd[7] = (byte) 0xAF;
                            break;
                        case 2:
                            cmd[7] = (byte) 0xB0;
                            break;
                        case 3:
                            cmd[7] = (byte) 0xB1;
                            break;
                    }
                    break;
                case TOGGLE:
                    //Command
                    cmd[6] = 0x03;
                    //checksum
                    switch (channel) {
                        case 0:
                            cmd[7] = (byte) 0xAF;
                            break;
                        case 1:
                            cmd[7] = (byte) 0xB0;
                            break;
                        case 2:
                            cmd[7] = (byte) 0xB1;
                            break;
                        case 3:
                            cmd[7] = (byte) 0xB2;
                            break;
                    }
                    break;
                case MOMENTARY:
                    //Command
                    cmd[6] = 0x04;
                    //checksum
                    switch (channel) {
                        case 0:
                            cmd[7] = (byte) 0xB0;
                            break;
                        case 1:
                            cmd[7] = (byte) 0xB1;
                            break;
                        case 2:
                            cmd[7] = (byte) 0xB2;
                            break;
                        case 3:
                            cmd[7] = (byte) 0xB3;
                            break;
                    }
                    break;
            }

            return cmd;
        }

        @Override
        public byte[] getReturnCommand(CommandReturn response, int channel) {
            if (response == null) {
                throw new IllegalArgumentException("Command purpose must be set");
            }

            if (channel < 0 || channel > 3) {
                throw new IllegalArgumentException("Channel must be between 0 and 3");
            }

            if (response == CommandReturn.READING_STATE) {
                return READING_STATE_RESPONSE;
            }

            byte[] cmd = new byte[8];

            //Frame head
            cmd[0] = 0x33;
            cmd[1] = 0x3C;

            //Reserved bytes
            cmd[2] = 0x00;
            cmd[3] = 0x00;
            cmd[4] = 0x00;

            switch (channel) {
                case 0:
                    cmd[5] = 0x01;
                    break;
                case 1:
                    cmd[5] = 0x02;
                    break;
                case 2:
                    cmd[5] = 0x03;
                    break;
                case 3:
                    cmd[5] = 0x04;
                    break;
            }

            switch (response) {
                case OPEN:
                    //Command
                    cmd[6] = 0x01;
                    //checksum
                    switch (channel) {
                        case 0:
                            cmd[7] = (byte) 0x71;
                            break;
                        case 1:
                            cmd[7] = (byte) 0x72;
                            break;
                        case 2:
                            cmd[7] = (byte) 0x73;
                            break;
                        case 3:
                            cmd[7] = (byte) 0x74;
                            break;
                    }
                    break;
                case CLOSE:
                    //Command
                    cmd[6] = 0x02;
                    //checksum
                    switch (channel) {
                        case 0:
                            cmd[7] = (byte) 0x72;
                            break;
                        case 1:
                            cmd[7] = (byte) 0x73;
                            break;
                        case 2:
                            cmd[7] = (byte) 0x74;
                            break;
                        case 3:
                            cmd[7] = (byte) 0x75;
                            break;
                    }
                    break;
            }

            return cmd;
        }
    };
}
