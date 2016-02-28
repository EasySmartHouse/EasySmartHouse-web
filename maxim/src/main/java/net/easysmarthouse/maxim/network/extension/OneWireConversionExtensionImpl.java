/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.maxim.network.extension;

import net.easysmarthouse.network.exception.ConversionException;
import net.easysmarthouse.network.extension.ConversionExtension;
import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;

/**
 *
 * @author rusakovich
 */
public class OneWireConversionExtensionImpl extends AdapterProviderBaseExtension implements ConversionExtension {

    private static final int CONVERSION_NOT_COMPLETE_RESPONSE = 0x0FF;

    //This command begins a temperature conversion. No further data is required. 
    //The bus master has to enable a strong pullup for 0.5 seconds immediately 
    //after issuing this command.
    private static final int START_CONVERTION_COMMAND = 0x44;

    //This command can save time in a single drop bus system by allowing the bus
    //master to access the clock functions without providing the 64-bit ROM code. 
    //If more than one slave is present on the bus and, for example, a read 
    //command is issued following the Skip ROM command, data collision will 
    //occur on the bus as multiple slaves transmit simultaneously 
    //(open drain pull-downs will produce a wired-AND result).
    //The Skip ROM command is useful to address all DS1920s on the bus to do a 
    //temperature conversion. Sincethe DS1920 uses a special command set, other 
    //device types will not respond to these commands.
    private static final int SKIP_ROM_COMMAND = 0xCC;

    public static final long DEFAULT_CONVERTION_TIME = 800;

    private long conversionTime = DEFAULT_CONVERTION_TIME;

    /**
     * Interrupt power delivery and check if temperature conversion has
     * completed.
     */
    public void stopConvertion() throws OneWireIOException, OneWireException {
        //Sets the 1-Wire Network voltage to normal level. This method is used 
        //to disable 1-Wire conditions created by <code>startPowerDelivery</code> 
        //and <code>startProgramPulse</code>. 
        //This method will automatically be called if a communication method 
        //is called while an outstanding power command is taking place.
        adapter.setPowerNormal();
        //Gets a byte from the 1-Wire Network.
        if (adapter.getByte() != CONVERSION_NOT_COMPLETE_RESPONSE) {
            throw new OneWireIOException("Temperature conversion not complete");
        }
    }

    /**
     * Initiate temperature conversion for all sensors. This method send reset
     * pulse, then send 0x44 command and start power delivery.
     */
    public void startConversion() throws OneWireIOException, OneWireException {
        //Sets the new speed of data transfer on the 1-Wire Network.
        adapter.setSpeed(DSPortAdapter.SPEED_REGULAR);
        //Sends a Reset to the 1-Wire Network.
        //the result of the reset. Potential results are:
        //0 (RESET_NOPRESENCE) no devices present on the 1-Wire Network.
        //1 (RESET_PRESENCE) normal presence pulse detected on the 1-Wire Network indicating there is a device present.
        //2 (RESET_ALARM) alarming presence pulse detected on the 1-Wire Network indicating there is a device present and it is in the alarm condition. This is only provided by the DS1994/DS2404 devices.
        //3 (RESET_SHORT) indicates 1-Wire appears shorted. This can be transient conditions in a 1-Wire Network. Not all adapter types can detect this condition.
        adapter.reset();
        adapter.putByte(SKIP_ROM_COMMAND);
        //Sets the duration to supply power to the 1-Wire Network. This method 
        //takes a time parameter that indicates the program pulse length when 
        //the method startPowerDelivery().
        //0 (DELIVERY_HALF_SECOND) provide power for 1/2 second.
        //1 (DELIVERY_ONE_SECOND) provide power for 1 second.
        //2 (DELIVERY_TWO_SECONDS) provide power for 2 seconds.
        //3 (DELIVERY_FOUR_SECONDS) provide power for 4 seconds.
        //4 (DELIVERY_SMART_DONE) provide power until the the device is no longer drawing significant power.
        //5 (DELIVERY_INFINITE) provide power until the setPowerNormal() method is called.
        adapter.setPowerDuration(DSPortAdapter.DELIVERY_INFINITE);

        //Returns whether the adapter can physically support strong 5 volt power mode.
        if (adapter.canDeliverPower()) {
            //Sets the 1-Wire Network voltage to supply power to a 1-Wire device. 
            //Note: to avoid getting an exception, use the canDeliverPower() and canDeliverSmartPower()
            //method to check it's availability.
            //0 (CONDITION_NOW) operation should occur immediately.
            //1 (CONDITION_AFTER_BIT) operation should be pending execution immediately after the next bit is sent.
            //2 (CONDITION_AFTER_BYTE) operation should be pending execution immediately after next byte is sent.
            adapter.startPowerDelivery(DSPortAdapter.CONDITION_AFTER_BYTE);
        }
        //Sends a byte to the 1-Wire Network.
        adapter.putByte(START_CONVERTION_COMMAND);
    }

    private void waitConversion() {
        try {
            Thread.sleep(conversionTime);
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void convert() throws ConversionException {
        try {
            this.startConversion();
            this.waitConversion();
            this.stopConvertion();
        } catch (Exception ex) {
            throw new ConversionException(ex);
        }
    }

    public long getConversionTime() {
        return conversionTime;
    }

    public void setConversionTime(long conversionTime) {
        this.conversionTime = conversionTime;
    }

}
