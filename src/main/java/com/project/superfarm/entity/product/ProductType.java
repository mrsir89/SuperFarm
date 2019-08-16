package com.project.superfarm.entity.product;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "product_type")
@Setter@Getter@ToString
public class ProductType {


    @Id
    @Column(name="product_type_code",updatable = false,nullable = false)
    private Long productTypeCode;

    @Column(name="product_type_name")
    private String productTypeName;

    @Column(name="product_unit")
    private String productUnit;

    @Column(name="product_origin")
    private String productOrigin;

}
