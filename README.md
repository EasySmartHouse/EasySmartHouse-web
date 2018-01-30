# Easy SmartHouse ![SmartHouse Logo](https://raw.githubusercontent.com/EasySmartHouse/EasySmartHouse-aux/master/logos/1_Primary_logo_on_transparent.png)

Easy SmartHouse project is an attempt to create an universal platform for the management of home devices using popular interfaces. At the moment, you can work with devices on the **Serial**, **USB**, **1-Wire** and **ZigBee** protocols, survey and secure your estate using a large number of supported cameras, configure devices interactions via *triggers*. To add support for the device is quite easy - you just need to add it in one of the configuration files.
Also, some kinds of mock-devices are available for development stage.

# Usage
1. Install [JRE 1.7](http://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase7-521261.html "JRE 1.7")
2. Download application release: [EasySmartHouse-0.1-release.zip](https://github.com/EasySmartHouse/EasySmartHouse-web/releases/download/0.1/EasySmartHouse-0.1-release.zip "EasySmartHouse-0.1-release.zip") and unpack it.
3. Execute **back.bat** or **back.sh** to run service part of the app.
4. Run **front.bat** or **front.sh** to start frontend.
5. Go to [http://localhost:8080/webui](http://localhost:8080/webui "http://localhost:8080/webui")

All the configs of the application are localed in **config** folder. 

# Features
### Embedded expression language ###
For complex logic devices control a special expression language was developed. 
Example of expression: 
> ***$${(device1 present) and ((sensor2>34.44) and (sensor1<3.45))}***

### Device's behaviour in script ###
Very useful and flexible feature that allows to describe, control and change devices behaviour in runtime without reloading the application. 
Example of JS-device, that describes 2-channel relay connected to COM-port may be found [here](https://github.com/EasySmartHouse/EasySmartHouse-web/blob/master/scripting/src/test/resources/network-integration-serial/switch.js "here").
Script syntax, in accordance with [**'Scripting Java' documentation**](https://developer.mozilla.org/en-US/docs/Mozilla/Projects/Rhino/Scripting_Java).
Also ***Groovy*** and ***JRuby*** scripts can be executed.

### Security ###

In addition to standard authentication by login and password, two factor authentication using the [YubiKey](https://www.yubico.com/start/ "YubiKey") is also available.


### Project modules ###

The project consists of two main parts: services which represent devices and ui part. The remaining modules provide the network devices and its cooperation.

* ***service*** - devices services module;
* ***device*** - base network devices interfaces;
* ***network*** - smart house network interfaces/base classes;
* ***maxim*** - 1-wire provider network/devices implementation;
* ***hid*** - usb devices network implementation;
* ***serial*** - serial protocol devices communication;
* ***mocks*** - mock devices network, may be used in development environment;
* ***cameras*** - working with cameras;
* ***ui*** - user interfaces repo;
* ***el*** - device control expressions;
* ***scripting*** - device script configs;
* ***zigbee*** - ZigBee network via dongle;

### See also ###
[**jusbrelay**](https://github.com/creepid/jusbrelay) - related module for USB relay interaction

[**Easy SmartHouse-mobile**](https://github.com/EasySmartHouse/EasySmartHouse-mobile) - mobile version of the application 