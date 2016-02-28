/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.hid.device;

import java.io.Serializable;

/**
 *
 * @author rusakovich
 */
public class HidDescriptor implements Serializable {

    final int productId;
    final int vendorId;

    public HidDescriptor(int productId, int vendorId) {
        this.productId = productId;
        this.vendorId = vendorId;
    }

    public int getProductId() {
        return productId;
    }

    public int getVendorId() {
        return vendorId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + productId;
        result = prime * result + vendorId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        HidDescriptor other = (HidDescriptor) obj;

        if (productId != other.productId) {
            return false;
        }

        if (vendorId != other.vendorId) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return productId + ":" + vendorId;
    }

}
