package com.project.superfarm.entity.product;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="product")
@DynamicInsert
@DynamicUpdate @Setter @Getter
public class Product implements Serializable {

    @Id
    @Column(name = "product_code")
    private Long productCode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "lower_code")
    private Integer lowerCode;

    @Column(name ="product_board_num")
    private Long productBoardNum;

    @Column(name = "product_type_code")
    private Long productTypeCode;

    @Column(name = "product_option1")
    private String productOption1;

    @Column(name = "product_option2")
    private String productOption2;

    @Column(name = "product_price")
    private Double productPrice;

    @Column(name = "product_made_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date productMadeDate;

    @Column(name = "product_notax_price")
    private Double productNoTaxPrice;

    @Column(name = "product_taxprice")
    private Double productTaxPrice;

    @Column(name = "product_tax")
    private Double productTax;

    @Column(name = "product_stock")
    private Integer productStock;

    @Column(name = "product_total_sales")
    private Integer productTotalSales;

    @Column(name = "product_status")
    private String productStatus;

    @Column(name = "farmer_id")
    private String farmerId;


    @OneToOne(fetch=FetchType.EAGER )
    @JoinColumn(name="product_type_code",referencedColumnName = "product_type_code",
            insertable = false, updatable = false)
    private ProductType productType;


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

    public Integer getLowerCode() {
        return lowerCode;
    }

    public void setLowerCode(Integer lowerCode) {
        this.lowerCode = lowerCode;
    }

    public Long getProductBoardNum() {
        return productBoardNum;
    }

    public void setProductBoardNum(Long productBoardNum) {
        this.productBoardNum = productBoardNum;
    }

    public Long getProductTypeCode() {
        return productTypeCode;
    }

    public void setProductTypeCode(Long productTypeCode) {
        this.productTypeCode = productTypeCode;
    }

    public String getProductOption1() {
        return productOption1;
    }

    public void setProductOption1(String productOption1) {
        this.productOption1 = productOption1;
    }

    public String getProductOption2() {
        return productOption2;
    }

    public void setProductOption2(String productOption2) {
        this.productOption2 = productOption2;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Date getProductMadeDate() {
        return productMadeDate;
    }

    public void setProductMadeDate(Date productMadeDate) {
        this.productMadeDate = productMadeDate;
    }

    public Double getProductNoTaxPrice() {
        return productNoTaxPrice;
    }

    public void setProductNoTaxPrice(Double productNoTaxPrice) {
        this.productNoTaxPrice = productNoTaxPrice;
    }

    public Double getProductTaxPrice() {
        return productTaxPrice;
    }

    public void setProductTaxPrice(Double productTaxPrice) {
        this.productTaxPrice = productTaxPrice;
    }

    public Double getProductTax() {
        return productTax;
    }

    public void setProductTax(Double productTax) {
        this.productTax = productTax;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public Integer getProductTotalSales() {
        return productTotalSales;
    }

    public void setProductTotalSales(Integer productTotalSales) {
        this.productTotalSales = productTotalSales;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}



