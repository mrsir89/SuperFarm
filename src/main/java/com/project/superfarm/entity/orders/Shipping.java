package com.project.superfarm.entity.orders;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="shipping")
@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@ToString
public class Shipping {

    @Id
    @Column(name="shipping_num",nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shippingNum;

    @Column(name="order_num")
    private Long orderNum;

    @Column(name="tracking_number")
    private String trackingNum;

    @Column(name="shipping_courier")
    private String shippingCourier;

    @Column(name="delivery_address")
    private String deliveryAddress;

    @Column(name="shipping_method")
    private String shippingMethod;

    @Column(name="shipping_price")
    private Double shippingPrice;

    @Column(name="shipping_status")
    private String shippingStatus;

    @Column(name="shipping_arrivel_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shippingArrivelDate;

    @Column(name="shipping_reciever")
    private String shippingReciever;

    @Column(name="shipping_reciever_phone")
    private String shippingRecieverPhone;

    @Column(name="shipping_reciever_phone2")
    private String shippingRecieverPhone2;


    @Column(name="shipping_memo")
    private String shippingMemo;

//    @OneToOne
//    @JoinColumn(name="shipping_num",referencedColumnName = "shipping_num")
//    private ShippingItem shippingItem;
}
