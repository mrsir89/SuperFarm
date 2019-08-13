package com.project.superfarm.entity.orders;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="shipping")
@Setter
@Getter
@ToString
public class Shipping {

    @Id
    @Column(name="shipping_num",nullable = false, updatable = false)
    private Long shippingNum;

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
    private Date shippingArrivelDate;

    @Column(name="shipping_reciever")
    private String shippingReciever;

    @Column(name="shipping_reciever_phone")
    private String shippingRecieverPhone;

    @Column(name="shipping_memo")
    private String shippingMemo;

}
