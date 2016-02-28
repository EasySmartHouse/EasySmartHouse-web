/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.network.extension;

import net.easysmarthouse.network.exception.ConversionException;

/**
 *
 * @author rusakovich
 */
public interface ConversionExtension {

    /**
     * Initiate conversion and wait for it.
     */
    public void convert() throws ConversionException;

}
