#include "lusb0_usb.h"

#define DEVICE_INTERFACE 0x01
#define TIMEOUT 5000

usb_dev_handle* setup_libusb_access(unsigned int product_id, unsigned int vendor_id);
void ini_control_transfer(usb_dev_handle *dev);
void temp_control_transfer(usb_dev_handle *dev);
void ini1_control_transfer(usb_dev_handle *dev);
void ini2_control_transfer(usb_dev_handle *dev);
void interrupt_read(usb_dev_handle *dev);
void interrupt_read_temperatura(usb_dev_handle *dev, float *tempC);
void close(usb_dev_handle *dev);