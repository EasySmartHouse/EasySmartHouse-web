/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.network.extension;

import net.easysmarthouse.network.exception.ConversionException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author mirash
 */
public class CompositeConversionExtension implements ConversionExtension {
    
    private List<ConversionExtension> extensions = Collections.synchronizedList(
            new LinkedList<ConversionExtension>());
    
    @Override
    public void convert() throws ConversionException {
        for (ConversionExtension conversionExtension : extensions) {
            conversionExtension.convert();
        }
    }
    
    public void setExtensions(List<ConversionExtension> extensions) {
        this.extensions = extensions;
    }
    
    public void add(ConversionExtension extension) {
        extensions.add(extension);
    }
    
}
