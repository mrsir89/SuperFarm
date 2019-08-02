package com.example.spring.catalog.model;

import com.example.spring.catalog.entity.MerchantProduct;

import java.io.Serializable;
import java.util.Collection;

public class CreateComposite implements Serializable {

    private MerchantProduct parent;

    private Collection<CompositeItem> items;

    public MerchantProduct getParent() {
        return parent;
    }

    public void setParent(MerchantProduct parent) {
        this.parent = parent;
    }

    public Collection<CompositeItem> getItems() {
        return items;
    }

    public void setItems(Collection<CompositeItem> items) {
        this.items = items;
    }
}
