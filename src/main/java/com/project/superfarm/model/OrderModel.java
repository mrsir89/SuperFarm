package com.project.superfarm.model;


import com.project.superfarm.entity.orders.OrderItems;
import com.project.superfarm.entity.orders.Orders;
import com.project.superfarm.entity.orders.Shipping;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class OrderModel {

    // Orders 관련
    private Long userNum;

    private String paymentMethod;

    private double orderTotalPrice;

    private String orderMemo;

    //shipping 관련
    private String deleveryAddress;

    private String shippngMethod;

    private double shippngPrice;

    private String shippingReciever;

    private String shippingrecieverPhone;

    private String shippingMemo;

    private List<OrderItems> orderItemsList;


    public Orders getOrder() {

        Orders order = new Orders();
        order.setUserNum(userNum);
        order.setPaymentMethod(paymentMethod);
        order.setOrderTotalPrice(orderTotalPrice);
        order.setOrderMemo(orderMemo);
        return order;

    }

    public Shipping getShipping() {

        Shipping shipping = new Shipping();
        shipping.setDeliveryAddress(deleveryAddress);
        shipping.setShippingMethod(shippngMethod);
        shipping.setShippingPrice(shippngPrice);
        shipping.setShippingReciever(shippingReciever);
        shipping.setShippingRecieverPhone(getShippingrecieverPhone());
        shipping.setShippingMemo(shippingMemo);
        return shipping;
    }
}
