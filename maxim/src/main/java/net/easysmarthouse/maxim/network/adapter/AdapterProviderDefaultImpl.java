/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.maxim.network.adapter;

import com.dalsemi.onewire.OneWireAccessProvider;
import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;

/**
 *
 * @author rusakovich
 */
public class AdapterProviderDefaultImpl extends AbstractAdapterProvider {

    public static final String DEFAULT_ADAPTER_PROPERTY = "onewire.adapter.default";
    public static final String DEFAULT_PORT_PROPERTY = "onewire.port.default";

    @Override
    protected DSPortAdapter retrieveAdapter() throws OneWireIOException, OneWireException {
        return OneWireAccessProvider.getDefaultAdapter();
    }

}
