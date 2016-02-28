/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.maxim.network.extension;

import com.dalsemi.onewire.adapter.DSPortAdapter;

/**
 *
 * @author rusakovich
 *
 * Base extension interface. All extension should implement this interface to
 * get access to <code>DSPortAdapter</code>
 */
public interface AdapterProviderExtension {

    public DSPortAdapter getAdapter();

    public void setAdapter(DSPortAdapter adapter);
}
