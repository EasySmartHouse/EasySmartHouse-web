/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.maxim.network.adapter;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;

/**
 *
 * @author rusakovich
 */
public interface AdapterProvider {

    /**
     * Returns current 1-Wire adapter.
     */
    public DSPortAdapter getAdapter() throws OneWireIOException, OneWireException;
}
