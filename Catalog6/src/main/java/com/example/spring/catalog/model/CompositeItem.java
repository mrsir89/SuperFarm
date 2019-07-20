package com.example.spring.catalog.model;

import java.io.Serializable;

public class CompositeItem implements Serializable {

    private String itemId;

    private Double quantity;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
