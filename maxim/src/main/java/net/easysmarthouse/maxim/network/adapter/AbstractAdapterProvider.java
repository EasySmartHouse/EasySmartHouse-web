/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.maxim.network.adapter;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;

/**
 *
 * @author rusakovich
 */
abstract class AbstractAdapterProvider implements AdapterProvider {

    protected DSPortAdapter adapter;

    /**
     * Returns 1-Wire network adapter, use <code>retrieveAdapter()</code>
     * function to get adapter..
     */
    @Override
    public DSPortAdapter getAdapter() throws OneWireIOException, OneWireException {
        if (adapter == null) {
            adapter = retrieveAdapter();
        }
        return adapter;
    }

    /**
     * Returns <code>DSPortAdapter</code> implementation.
     */
    protected abstract DSPortAdapter retrieveAdapter() throws OneWireIOException, OneWireException;

}
