/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.maxim.network.adapter;

import com.dalsemi.onewire.OneWireAccessProvider;
import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author rusakovich
 */
public class AdapterProviderImpl extends AbstractAdapterProvider {

    private Log log = LogFactory.getLog(AdapterProviderImpl.class);

    private String adapterName;
    private String portName;

    public String getAdapterName() {
        return adapterName;
    }

    public void setAdapterName(String adapterName) {
        this.adapterName = adapterName;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    /**
     * Returns 1-Wire network adapter, adapter type and port, determine by
     * object properties.
     */
    @Override
    protected DSPortAdapter retrieveAdapter() throws OneWireIOException, OneWireException {
        if (log.isDebugEnabled()) {
            log.debug("Retriewe adapter, adapterName: " + adapterName + "; portName:" + portName);
        }        
        return OneWireAccessProvider.getAdapter(adapterName, portName);
    }

}
