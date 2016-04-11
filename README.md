# Easy SmartHouse ![SmartHouse Logo](https://raw.githubusercontent.com/EasySmartHouse/EasySmartHouse-aux/master/logos/1_Primary_logo_on_transparent.png)

Easy SmartHouse project is an attempt to create an universal platform for the management of home devices using popular interfaces. At the moment, you can work with devices on the **Serial**, **USB**, **1-Wire** and **ZigBee** protocols, survey and secure your estate using a large number of supported cameras. To add support for the device is quite easy - you just need to add it in one of the configuration files.
Also, some kinds of mock-devices are available for development stage.

### Embedded expression language ###
For complex logic devices control a special expression language was developed. 
Example of expression: 
> ***$${(device1 present) and ((sensor2>34.44) and (sensor1<3.45))}***

### Device settings in script ###
Very useful and flexible feature that allows to control and change devices behaviour in runtime. 
Example of script:
```javascript 
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
```

### Some screenshots ###
For now, ***web UI*** is only available 
*(click on image to view full size)*.

[![Login page][2]][1] [![Climate control page][4]][3] [![Cameras page][6]][5] [![Light control page][8]][7] [![Signaling elements page][10]][9]
  [1]: https://raw.githubusercontent.com/EasySmartHouse/EasySmartHouse-aux/master/screenshots/esh-login.png
  [2]: https://raw.githubusercontent.com/EasySmartHouse/EasySmartHouse-aux/master/screenshots/esh-login-180x109.jpg
 (Login page)
  [3]: https://raw.githubusercontent.com/EasySmartHouse/EasySmartHouse-aux/master/screenshots/esh-monitorings.png
  [4]: https://raw.githubusercontent.com/EasySmartHouse/EasySmartHouse-aux/master/screenshots/esh-monitorings-180x109.jpg
(Climate control page)
  [5]: https://raw.githubusercontent.com/EasySmartHouse/EasySmartHouse-aux/master/screenshots/esh-cameras.png
  [6]: https://raw.githubusercontent.com/EasySmartHouse/EasySmartHouse-aux/master/screenshots/esh-cameras-180x109.jpg
(Cameras page)
  [7]: https://raw.githubusercontent.com/EasySmartHouse/EasySmartHouse-aux/master/screenshots/esh-signaling.png
  [8]: https://raw.githubusercontent.com/EasySmartHouse/EasySmartHouse-aux/master/screenshots/esh-switching-180x109.jpg
(Light control page)
  [9]: https://raw.githubusercontent.com/EasySmartHouse/EasySmartHouse-aux/master/screenshots/esh-signaling.png
  [10]: https://raw.githubusercontent.com/EasySmartHouse/EasySmartHouse-aux/master/screenshots/esh-signaling-180x109.jpg
(Signaling elements page)


### Project modules ###

The project consists of two main parts: services which represent devices and ui part. The remaining modules provide the network devices and its cooperation.

* ***service*** - devices services module
* ***device*** - base network devices interfaces 
* ***network*** - smart house network interfaces/base classes
* ***maxim*** - 1-wire provider network/devices implementation
* ***hid*** - usb devices network implementation
* ***serial*** - serial protocol devices communication
* ***mocks*** - mock devices network, may be used in development environment 
* ***cameras*** - working with cameras
* ***ui*** - user interfaces repo 
* ***el*** - device control expressions 
* ***scripting*** - device script configs  

### Testing ###

* ***network / src / test /*** - base network unit/integration tests 
* ***maxim / src / test /*** - 1-wire network unit/integration tests
* ***service / src / test /*** - services client tests
* ***mocks / src / test /*** - mock devices and network test
* ***hid / src / test /*** - hid network test
* ***serial / src / test /*** - serial communication test
* ***cameras / src / test /*** - cameras interaction
* ***ui / webui / src / test /*** - user interface tests
* ***el / src / test /*** - expressions tests
* ***scripting / src / test /*** - tests of script devices  

### See also ###
[**jusbrelay**](https://github.com/creepid/jusbrelay) - related module for USB relay interaction  