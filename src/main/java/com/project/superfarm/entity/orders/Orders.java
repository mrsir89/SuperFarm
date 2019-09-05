package com.project.superfarm.entity.orders;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@ToString
public class Orders implements Serializable {


    @Id
    @Column(name= "order_num",nullable = false,updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNum;

    @Column(name="user_num",nullable = false, updatable = false)
    private Long userNum;

    @Column(name="order_record_bill")
    private String orderRecordBill;

    @UpdateTimestamp
    @Column(name="order_payment_date")
    private Date orderPaymentDate;

    @Column(name="payment_method")
    private String PaymentMethod;

    @Column(name="order_sell_date")
    @CreationTimestamp
    private Date orderSellDate;

    @Column(name="order_total_price")
    private double orderTotalPrice;

    @Column(name="order_memo")
    private String orderMemo;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "orders_shipping",
            joinColumns = @JoinColumn(name = "order_num"),
            inverseJoinColumns = @JoinColumn(name = "order_item_num")
    )
    private List<OrderItems> orderItemSet;



    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "orders_shipping",
            joinColumns = @JoinColumn(name = "order_num"),
            inverseJoinColumns = @JoinColumn(name = "shipping_num")
    )
    private List<Shipping> shippings;

//    @OneToMany
//    @JoinColumn(name="order_num")
//    private List<Shipping> shipping;
//
//    @OneToMany
//    @JoinColumn(name="order_num")
//    private List<OrderItems> orderItemSet;
}
