# SmartHouse ![SmartHouse Logo](http://s7.postimg.org/7hkfs9f8b/Smart_House_Logo.png)

SmartHouse project is an attempt to create a universal platform for the management of home devices using popular interface interaction. At the moment, you can work with devices on the **serial**, **USB** and **1-Wire** protocols, survey and secure your estate using a large number of supported cameras. To add support for the device is quite easy - you just need to add it in one of the configuration files.
Also, some kinds of mock-devices are available for development stage.

### Embedded expression language ###
For complex logic devices control a special expression language was developed. 
Example of expression: 
> ***$${(device1 present) and ((sensor2>34.44) and (sensor1<3.45))}***

### Some screenshots ###
For now, ***web UI*** is only available 
*(click on image to view full size)*.

[![Login page][2]][1] [![Climate control page][4]][3] [![Cameras page][6]][5] [![Light control page][8]][7] [![Signaling elements page][10]][9]
  [1]: http://postimg.org/image/ofvjupr41/
  [2]: http://s16.postimg.org/ofvjupr41/sm_login.jpg
 (Login page)
  [3]: http://postimg.org/image/jozj9k11x/
  [4]: http://s10.postimg.org/jozj9k11x/sm_climate_control.jpg
(Climate control page)
  [5]: http://postimg.org/image/pjqse8iwv/
  [6]: http://s27.postimg.org/pjqse8iwv/sm_cameras.jpg
(Cameras page)
  [7]: http://postimg.org/image/b84pteumx/
  [8]: http://s28.postimg.org/b84pteumx/sm_light_control.jpg
(Light control page)
  [9]: http://postimg.org/image/muvftvx7x/
  [10]:http://s22.postimg.org/muvftvx7x/sm_signaling.jpg
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

### See also ###
[**jusbrelay**](https://github.com/creepid/jusbrelay) - related module for USB relay interaction  