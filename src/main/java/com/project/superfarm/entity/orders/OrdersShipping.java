package com.project.superfarm.entity.orders;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="orders_shipping")
@Setter
@Getter
@ToString
public class OrdersShipping implements Serializable {

    @Column(name="order_num")
    private Long orderNum;


    @Column(name="shipping_num")
    private Long shippingNum;

    @Id
    @Column(name="order_item_num")
    private Long orderItemNum;


    @Transient
    private Orders oders;

    @Transient
    Set<Shipping> shippings;

    @Transient
    Set<OrderItems> orderItems;

}
