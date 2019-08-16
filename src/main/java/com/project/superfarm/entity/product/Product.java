package com.project.superfarm.entity.product;


import com.project.superfarm.entity.board.ProductBoard;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Column(name = "product_type_code")
    private Long productTypecode;

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
    private Double productNotaxPrice;

    @Column(name = "product_taxprice")
    private Double productTaxprice;

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

//    @ManyToMany()
//    private List<ProductBoard> productList;


//
//    @OneToOne(fetch = FetchType.EAGER)
//    private ProductType productType;

}



