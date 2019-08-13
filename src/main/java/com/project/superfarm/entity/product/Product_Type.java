package com.project.superfarm.entity.product;


import javax.persistence.*;

@Entity
@Table(name = "product_type")
public class Product_Type {

    @Id
    @Column(name="product_type_code",updatable = false,nullable = false)
    private Long productTypeCode;

    @Column(name="product_type_name")
    private String productTypeName;

    @Column(name="product_unit")
    private String productUnit;

    @Column(name="product_origin")
    private String productOrigin;



    public Long getProductTypeCode() {
        return productTypeCode;
    }

    public void setProductTypeCode(Long productTypeCode) {
        this.productTypeCode = productTypeCode;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getProductOrigin() {
        return productOrigin;
    }

    public void setProductOrigin(String productOrigin) {
        this.productOrigin = productOrigin;
    }
}
