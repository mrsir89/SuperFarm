package com.project.superfarm.model;


import com.project.superfarm.entity.orders.OrderItems;
import com.project.superfarm.entity.orders.Orders;
import com.project.superfarm.entity.orders.Shipping;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@ToString
@Getter
@Setter
public class OrderModel implements Serializable {

    // Orders 관련
    private Long userNum;

    private String paymentMethod;

    private double orderTotalPrice;

    private String orderMemo;

    //shipping 관련
    private String deliveryAddress;

    private String shippingMethod;

    private double shippingPrice;

    private String shippingReceiver;

    private String shippingReceiverPhone;

    private String shippingReceiverPhone2;

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
        shipping.setDeliveryAddress(deliveryAddress);
        shipping.setShippingMethod(shippingMethod);
        shipping.setShippingPrice(shippingPrice);
        shipping.setShippingReciever(shippingReceiver);
        shipping.setShippingRecieverPhone(getShippingReceiverPhone());
        shipping.setShippingRecieverPhone2(getShippingReceiverPhone2());
        shipping.setShippingMemo(shippingMemo);
        return shipping;
    }
}
