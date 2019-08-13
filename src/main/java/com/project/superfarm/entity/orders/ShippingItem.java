package com.project.superfarm.entity.orders;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="shipping_item")
@Setter
@Getter
@ToString
public class ShippingItem implements Serializable {

    @Id
    @Column(name="shipping_num")
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
    private Date orderSellDate;

}
