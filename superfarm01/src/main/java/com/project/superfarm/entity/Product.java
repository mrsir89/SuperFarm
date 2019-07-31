package com.project.superfarm.entity;

import javax.persistence.*;

@Entity
@Table(name = "product", uniqueConstraints = {@UniqueConstraint(columnNames = "product_code")})
public class Product {

    @Id
    @Column(name = "product_code", nullable = false, updatable = false)
    private Long productCode;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "lower_code", nullable = false)
    private String lowerCode;

    @Column(name = "product_type", nullable = false)
    private String productType;

    @Column(name = "product_option1")
    private String productFirstOption;

    @Column(name = "product_option2")
    private String productSecondOption;

    @Column(name = "product_price", nullable = false)
    private int productPrice;

    @Column(name = "product_made_date")
    private int productMadeDate;

    @Column(name = "product_notax_price")
    private Integer productNotaxPrice;

    @Column(name = "product_taxprice")
    private Integer productTaxPrice;

    @Column(name = "tax")
    private Integer tax;

    @Column(name = "product_stock")
    private Integer productStock;

    @Column(name = "product_total_sales")
    private Integer procudtTotalSales;

    @Column(name = "product_maker")
    private String productMaker;

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLowerCode() {
        return lowerCode;
    }

    public void setLowerCode(String lowerCode) {
        this.lowerCode = lowerCode;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductFirstOption() {
        return productFirstOption;
    }

    public void setProductFirstOption(String productFirstOption) {
        this.productFirstOption = productFirstOption;
    }

    public String getProductSecondOption() {
        return productSecondOption;
    }

    public void setProductSecondOption(String productSecondOption) {
        this.productSecondOption = productSecondOption;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductMadeDate() {
        return productMadeDate;
    }

    public void setProductMadeDate(int productMadeDate) {
        this.productMadeDate = productMadeDate;
    }

    public Integer getProductNotaxPrice() {
        return productNotaxPrice;
    }

    public void setProductNotaxPrice(Integer productNotaxPrice) {
        this.productNotaxPrice = productNotaxPrice;
    }

    public Integer getProductTaxPrice() {
        return productTaxPrice;
    }

    public void setProductTaxPrice(Integer productTaxPrice) {
        this.productTaxPrice = productTaxPrice;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public Integer getProcudtTotalSales() {
        return procudtTotalSales;
    }

    public void setProcudtTotalSales(Integer procudtTotalSales) {
        this.procudtTotalSales = procudtTotalSales;
    }

    public String getProductMaker() {
        return productMaker;
    }

    public void setProductMaker(String productMaker) {
        this.productMaker = productMaker;
    }
}
