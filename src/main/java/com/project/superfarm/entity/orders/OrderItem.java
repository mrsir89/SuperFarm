package com.project.superfarm.entity.orders;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order_itmes")
@ToString
@Setter
@Getter
public class OrderItem {

    @Id
    @Column(name="order_item_num",updatable = false,nullable = false)
    private Long orderItemNum;

    @Column(name="product_code")
    private Long orderItemGroup;

    @Column(name="order_item_group")
    private Integer orderItemDepth;

    @Column(name="order_item_depth")
    private Long productCode;

    @Column(name="order_item_price")
    private Double orderItemPrice;

    @Column(name="order_count")
    private int orderCount;

    @Column(name="order_item_send_status")
    private String orderItemSendStatus;

    @Column(name="order_item_status")
    private String orderItemStatus;

    @Column(name="order_shipping_memo")
    private String orderShippingMemo;

}
