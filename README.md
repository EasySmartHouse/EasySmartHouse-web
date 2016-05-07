# Easy SmartHouse ![SmartHouse Logo](https://raw.githubusercontent.com/EasySmartHouse/EasySmartHouse-aux/master/logos/1_Primary_logo_on_transparent.png)
## [![Build Status](https://travis-ci.org/EasySmartHouse/EasySmartHouse-web.svg)](https://travis-ci.org/EasySmartHouse/EasySmartHouse-web)##
Easy SmartHouse project is an attempt to create an universal platform for the management of home devices using popular interfaces. At the moment, you can work with devices on the **Serial**, **USB**, **1-Wire** and **ZigBee** protocols, survey and secure your estate using a large number of supported cameras. To add support for the device is quite easy - you just need to add it in one of the configuration files.
Also, some kinds of mock-devices are available for development stage.

### Embedded expression language ###
For complex logic devices control a special expression language was developed. 
Example of expression: 
> ***$${(device1 present) and ((sensor2>34.44) and (sensor1<3.45))}***

### Device's behaviour in script ###
Very useful and flexible feature that allows to describe, control and change devices behaviour in runtime. 
Example of JavaScript, that describes 2-channel relay connected to COM-port:
```javascript
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
```
Script syntax, in accordance with [**'Scripting Java' documentation**](https://developer.mozilla.org/en-US/docs/Mozilla/Projects/Rhino/Scripting_Java).
Also ***Groovy*** and ***JRuby*** scripts can be executed.

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
* ***scripting / src / test /*** - tests and examples of script devices  

### See also ###
[**jusbrelay**](https://github.com/creepid/jusbrelay) - related module for USB relay interaction  