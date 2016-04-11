var device = {
    address: "COM3",
    label: 'Teapot switch',
    description: 'Teapot switch in the kitchen',
    deviceType: 'Actuator',
    actuatorType: 'switchActuator',
    port: null,
    state: false,
    init: function () {
        var PortType = Java.type("jssc.SerialPort");
        this.port = new PortType(this.address);
        var _port = this.port;
        _port.openPort();
        var PortClass = PortType.getClass();
        _port.setParams(PortClass.BAUDRATE_9600, PortClass.DATABITS_8, PortClass.STOPBITS_1, PortClass.PARITY_NONE);
    },
    destroy: function () {
        this.port.closePort();
    },
    setValue: function (state) {
        var comm = null;
        if (state) {
            //switch read command
            comm = [0x55, 0x56, 0x00, 0x00, 0x00, 0x01, 0x01, 0xAD];
            this.state = true;
        } else {
            //switch write command
            comm = [0x55, 0x56, 0x00, 0x00, 0x00, 0x01, 0x01, 0xAE];
            this.state = false;
        }
        var javaComm = Java.to(comm, "byte[]");
        this.port.writeBytes(javaComm);
    },
    getState: function () {
        return this.state;
    }
};
