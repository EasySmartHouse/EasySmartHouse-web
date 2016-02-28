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
 */
public class AdapterProviderBaseExtension implements AdapterProviderExtension {

    protected DSPortAdapter adapter;

    @Override
    public DSPortAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void setAdapter(DSPortAdapter adapter) {
        this.adapter = adapter;
    }

}
