package com.project.superfarm.entity.orders;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="order_shipping")
@Setter
@Getter
@ToString
public class OrderShipping implements Serializable {

    @Column(name="order_num")
    private Long orderNum;

    @Column(name="shipping_num")
    private Long shippingNum;

    @Column(name="order_item_num")
    private Long orderItemNum;

}
