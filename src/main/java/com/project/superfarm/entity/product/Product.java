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

}



