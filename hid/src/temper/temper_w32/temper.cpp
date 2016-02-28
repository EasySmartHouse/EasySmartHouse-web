#include "temper.h"
#include "device.h"
#include "jni.h"

#define VENDOR_ID  net_easysmarthouse_hid_device_natives_Temper_VENDOR_ID
#define PRODUCT_ID net_easysmarthouse_hid_device_natives_Temper_PRODUCT_ID


usb_dev_handle *lvr_winusb = NULL;

void throwJavaException(JNIEnv* pEnv, const char* pClazzName, const char* pMessage) {
	jclass clazz = pEnv->FindClass(pClazzName);
	if (clazz) {
		pEnv->ThrowNew(clazz, pMessage);
	}
	pEnv->DeleteLocalRef(clazz);
}

/*
 * Class:     net_easysmarthouse_hid_device_natives_Temper
 * Method:    init
 * Signature: (S)I
 */
JNIEXPORT jint JNICALL Java_net_easysmarthouse_hid_device_natives_Temper_init
(JNIEnv *pEnv, jobject, jshort){
	if ((lvr_winusb = setup_libusb_access(PRODUCT_ID, VENDOR_ID)) == NULL) {
		
		const char* clazzName = "net/easysmarthouse/hid/device/natives/NativeDeviceException";
		const char* message = "Cannot find PCsensor Temper device";
		throwJavaException(pEnv, clazzName, message);

		return -1;
	}

	ini_control_transfer(lvr_winusb);

	temp_control_transfer(lvr_winusb);
	interrupt_read(lvr_winusb);

	ini1_control_transfer(lvr_winusb);
	interrupt_read(lvr_winusb);

	ini2_control_transfer(lvr_winusb);
	interrupt_read(lvr_winusb);
	interrupt_read(lvr_winusb);

	return 0;
}

/*
 * Class:     net_easysmarthouse_hid_device_natives_Temper
 * Method:    readTemperature
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_net_easysmarthouse_hid_device_natives_Temper_readTemperature
(JNIEnv *pEnv, jobject){
	if (lvr_winusb == NULL) {
		
		const char* clazzName = "net/easysmarthouse/hid/device/natives/NativeDeviceException";
		const char* message = "Device did not initialized";
		throwJavaException(pEnv, clazzName, message);

		return -1;
	}

	float tempc;

	temp_control_transfer(lvr_winusb);
	interrupt_read_temperatura(lvr_winusb, &tempc);

	return tempc;
}

/*
 * Class:     net_easysmarthouse_hid_device_natives_Temper
 * Method:    close
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_net_easysmarthouse_hid_device_natives_Temper_close
(JNIEnv *pEnv, jobject){
	if (lvr_winusb == NULL) {
		
		const char* clazzName = "net/easysmarthouse/hid/device/natives/NativeDeviceException";
		const char* message = "Device did not initialized";
		throwJavaException(pEnv, clazzName, message);
	}

	close(lvr_winusb);
}