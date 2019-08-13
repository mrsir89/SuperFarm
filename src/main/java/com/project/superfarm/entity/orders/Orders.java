package com.project.superfarm.entity.orders;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="Orders")
@Getter
@Setter
@ToString
public class Orders implements Serializable {

    @Id
    @Column(name= "order_num",nullable = false,updatable = false)
    private Long orderNum;

    @Column(name="user_num",nullable = false, updatable = false)
    private Long userNum;

    @Column(name="order_record_bill")
    private String orderRecordBill;

    @Column(name="order_payment_date")
    private Date orderPaymentDate;

    @Column(name="payment_method")
    private String PaymentMethod;

    @Column(name="order_sell_date")
    @CreationTimestamp
    private Date orderSellDate;

    @Column(name="order_total_price")
    private double OrderTotalPrice;

    @Column(name="order_memo")
    private String orderMemo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name ="orders_shipping",
        joinColumns =@JoinColumn(name="order_num"),
        inverseJoinColumns = @JoinColumn(name="order_item_num"))
    private Set<OrderItem> orderItemSet;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="orders_shipping",
        joinColumns = @JoinColumn(name="order_num"),
        inverseJoinColumns = @JoinColumn(name="order_item_num"))
    private Set<Shipping> shippings;

}
