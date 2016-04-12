var device = {
    address: "COM4",
    label: 'Teapot switch',
    description: 'Teapot switch in the kitchen',
    deviceType: 'Actuator',
    actuatorType: 'switchActuator',
    port: null,
    state: false,
    init: function () {
        var PortType = Java.type("jssc.SerialPort");
        port = new PortType(this.address);
        port.openPort();
        var PortClass = port.getClass();
        port.setParams(PortClass.BAUDRATE_9600, PortClass.DATABITS_8, PortClass.STOPBITS_1, PortClass.PARITY_NONE);
    },
    destroy: function () {
       if (port){
           port.closePort();
       }
    },
    setValue: function (state) {
        var comm = null;
        if (state) {
            //switch on command
            comm = [0x55, 0x56, 0x00, 0x00, 0x00, 0x01, 0x01, 0xAD];
            this.state = true;
        } else {
            //switch off command
            comm = [0x55, 0x56, 0x00, 0x00, 0x00, 0x01, 0x01, 0xAE];
            this.state = false;
        }
        var javaComm = Java.to(comm, "byte[]");
        port.writeBytes(javaComm);
    },
    getState: function () {
        return this.state;
    }
};
