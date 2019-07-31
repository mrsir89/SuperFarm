package com.project.superfarm.model;

import com.project.superfarm.entity.Product;

public class CeateProduct {

    private String name;
    private String maker;
    private String lowerCode;
    private String type;
    private String firstOption;
    private String secondOption;
    private String price;
    private String madeDate;
    private String noTaxPrice;
    private String taxPrice;
    private String stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getLowerCode() {
        return lowerCode;
    }

    public void setLowerCode(String lowerCode) {
        this.lowerCode = lowerCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstOption() {
        return firstOption;
    }

    public void setFirstOption(String firstOption) {
        this.firstOption = firstOption;
    }

    public String getSecondOption() {
        return secondOption;
    }

    public void setSecondOption(String secondOption) {
        this.secondOption = secondOption;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMadeDate() {
        return madeDate;
    }

    public void setMadeDate(String madeDate) {
        this.madeDate = madeDate;
    }

    public String getNoTaxPrice() {
        return noTaxPrice;
    }

    public void setNoTaxPrice(String noTaxPrice) {
        this.noTaxPrice = noTaxPrice;
    }

    public String getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(String taxPrice) {
        this.taxPrice = taxPrice;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Product getProduct(){
        Product product=new Product();

        product.setProductName(name);
        product.setProductMaker(maker);
        product.setProductMadeDate(Integer.parseInt(madeDate));
        product.setLowerCode(lowerCode);
        product.setProductType(type);
        product.setProductFirstOption(firstOption);
        product.setProductSecondOption(secondOption);
        product.setProductPrice(Integer.parseInt(price));
        product.setProductNotaxPrice(Integer.parseInt(noTaxPrice));
        product.setProductNotaxPrice(Integer.parseInt(taxPrice));
        product.setProductNotaxPrice(Integer.parseInt(stock));

        return product;
    }
}
