package com.project.superfarm.entity.orders;

import com.project.superfarm.entity.product.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name="order_items")
@ToString
@DynamicUpdate
@DynamicInsert
@Setter
@Getter
public class OrderItems {

    @Id
    @Column(name="order_item_num",updatable = false,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemNum;

    @Column(name="order_num")
    private Long orderNum;

    @Column(name="order_item_group")
    private Long orderItemGroup;

    @Column(name="order_item_depth")
    private Integer orderItemDepth;

    @Column(name="product_code")
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_code",referencedColumnName = "product_code",
            insertable = false, updatable = false)
    private Product product;

}
