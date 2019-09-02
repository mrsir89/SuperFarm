package com.project.superfarm.entity.orders;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="shipping_item")
@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@ToString
public class ShippingItem implements Serializable {

    @Id
    @Column(name="shipping_num",nullable = false,updatable = false)
    private Long shippingNum;

    @Column(name="product_code")
    private Long productCode;

    @Column(name="product_name")
    private String productName;

    @Column(name="product_option1")
    private String productOption1;

    @Column(name="product_option2")
    private String productOption2;

    @Column(name="product_price")
    private Double productPrice;

    @Column(name="order_sell_date")
    @CreationTimestamp
    private Date orderSellDate;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="product_code",referencedColumnName = "product_code",
//            insertable = false, updatable = false)
//    private Product product;

}
