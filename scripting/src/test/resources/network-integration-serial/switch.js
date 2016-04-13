importPackage(Packages.jssc);
var Importer = JavaImporter(Packages.java.lang,
                            Packages.java.lang.reflect);

var device = {
    address: "COM11",
    label: 'Teapot switch',
    description: 'Teapot switch in the kitchen',
    deviceType: 'Actuator',
    actuatorType: 'switchActuator',
    port: null,
    state: false,
    init: function () {
        //set up port connection
        port = new SerialPort(this.address);
        //open port
        port.openPort();
        //set baudrate, speed, etc.
        port.setParams(port.BAUDRATE_9600, port.DATABITS_8, port.STOPBITS_1, port.PARITY_NONE);
    },
    destroy: function () {
        //close port
       if (port){
           port.closePort();
       }
    },
    setState: function (state) {
        //create java array
        var comm = null;
        with(Importer){
            comm = Array.newInstance(Integer.TYPE, 8);
        }
        //base sequence (the first channel)
        comm[0] = 0x55;
        comm[1] = 0x56;
        comm[2] = 0x00;
        comm[3] = 0x00;
        comm[4] = 0x00;
        comm[5] = 0x01;
        
        if (state) {
            //switch on sequence (the first channel)
            comm[6] = 0x01;
            comm[7] = 0xAD;
            this.state = true;
        } else {
            //switch off sequence (the first channel)
            comm[6] = 0x02;
            comm[7] = 0xAE;
            this.state = false;
        }
        
        //write to port
        port.writeIntArray(comm);
    },
    getState: function () {
        return this.state;
    }
};
