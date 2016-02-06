#include "device.h"
#include <stdio.h>
#include <signal.h>
#include "windows.h" 
#include "lusb0_usb.h"

#define bzero(b,len) (memset((b), '\0', (len)), (void) 0)

const static char uTemperatura[] = { 0x01, 0x80, 0x33, 0x01, 0x00, 0x00, 0x00, 0x00 };
const static char uIni1[] = { 0x01, 0x82, 0x77, 0x01, 0x00, 0x00, 0x00, 0x00 };
const static char uIni2[] = { 0x01, 0x86, 0xff, 0x01, 0x00, 0x00, 0x00, 0x00 };

const static int reqIntLen=8;

static int bsalir=1;
static int formato=1;
static int debug=0;
static int mrtg=0;

usb_dev_handle *find_lvr_winusb(unsigned int product_id, unsigned int vendor_id);

usb_dev_handle* setup_libusb_access(unsigned int product_id, unsigned int vendor_id) {
	usb_dev_handle *lvr_winusb;

	if(debug) {
		usb_set_debug(255);
	} else {
		usb_set_debug(0);
	}

	usb_init();
	usb_find_busses();
	usb_find_devices();


	if(!(lvr_winusb = find_lvr_winusb(product_id, vendor_id))) {
		printf("Couldn't find the USB device, Exiting\n");
		return NULL;
	}


	if (usb_set_configuration(lvr_winusb, 0x01) < 0) {
		printf("Could not set configuration 1\n");
		return NULL;
	}

	if (usb_claim_interface(lvr_winusb, DEVICE_INTERFACE) < 0) {
		printf("Could not claim interface\n");
		return NULL;
	}

	return lvr_winusb;
}

usb_dev_handle *find_lvr_winusb(unsigned int product_id, unsigned int vendor_id) {
	struct usb_bus *bus;
	struct usb_device *dev;

	for (bus = usb_busses; bus; bus = bus->next) {
		for (dev = bus->devices; dev; dev = dev->next) {

			if (dev->descriptor.idVendor == vendor_id && 
				dev->descriptor.idProduct == product_id ) {
					usb_dev_handle *handle;
					if(debug) {
						printf("lvr_winusb with Vendor Id: %x and Product Id: %x found.\n", vendor_id, product_id);
					}

					if (!(handle = usb_open(dev))) {
						printf("Could not open USB device\n");
						return NULL;
					}
					return handle;
			}
		}
	}
	return NULL;
}

void ini_control_transfer(usb_dev_handle *dev) {
	int r,i;
	char question[] = { 0x01,0x01 };
	r = usb_control_msg(dev, 0x21, 0x09, 0x0201, 0x00, (char *) question, 2, TIMEOUT);
	if( r < 0 )
	{
		perror("USB init control write");
	}
	if(debug) {
		for (i=0;i<reqIntLen; i++) printf("%02x ",question[i] & 0xFF);
		printf("\n");
	}
}

void control_transfer(usb_dev_handle *dev, const char *pquestion) {
	int r,i;
	char question[reqIntLen];
	memcpy(question, pquestion, sizeof question);
	r = usb_control_msg(dev, 0x21, 0x09, 0x0200, 0x01, (char *) question, reqIntLen, TIMEOUT);
	if( r < 0 )
	{
		perror("USB control write");
	}
	if(debug) {
		for (i=0;i<reqIntLen; i++) printf("%02x ",question[i] & 0xFF);
		printf("\n");
	}
}

void interrupt_read(usb_dev_handle *dev) {
	int r,i;
	char answer[reqIntLen];
	bzero(answer, reqIntLen);
	r = usb_interrupt_read(dev, 0x82, (char *) answer, reqIntLen, TIMEOUT);
	if( r != reqIntLen )
	{
		perror("USB interrupt read");
	}
	if(debug) {
		for (i=0;i<reqIntLen; i++) printf("%02x ",answer[i] & 0xFF);
		printf("\n");
	}
}

void interrupt_read_temperatura(usb_dev_handle *dev, float *tempC) {

	int r,i, temperature;
	unsigned char answer[reqIntLen];
	bzero(answer, reqIntLen);

	r = usb_interrupt_read(dev, 0x82, (char *) answer, reqIntLen, TIMEOUT);
	if( r != reqIntLen )
	{
		perror("USB interrupt read");
	}

	if(debug) {
		for (i=0;i<reqIntLen; i++) printf("%02x ",answer[i]  & 0xFF);

		printf("\n");
	}

	/* Temperature in C is a 16-bit signed fixed-point number, big-endian */
	temperature = (answer[3] & 0xFF) + (answer[2] << 8);
	*tempC = temperature * (125.0f / 32000.0f);
}

void close(usb_dev_handle *dev){
	usb_release_interface(dev, DEVICE_INTERFACE);
	usb_close(dev); 
}

void temp_control_transfer(usb_dev_handle *dev){
	control_transfer(dev, uTemperatura );;
}

void ini1_control_transfer(usb_dev_handle *dev){
	control_transfer(dev, uIni1 );
}

void ini2_control_transfer(usb_dev_handle *dev){
	control_transfer(dev, uIni2 );
}