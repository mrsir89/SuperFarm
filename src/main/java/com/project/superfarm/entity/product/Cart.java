package com.project.superfarm.entity.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="cart")
@Setter
@Getter @ToString
@DynamicUpdate
@DynamicInsert
public class Cart implements Serializable {

    @Id
    @Column(name="cart_num",nullable = false,updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartNum;

    @Column(name= "user_num",updatable = false,nullable = false)
    private Long   userNum;

    @Column(name="product_board_title")
    private String productBoardTitle;

    @Column(name="product_board_num")
    private Long   productBoardNum;

    @Column(name="cart_product_name")
    private String cartProductName;

    @Column(name="product_code")
    private Long productCode;

    @Column(name="cart_product_option1")
    private String cartProductOption1;

    @Column(name="cart_product_option2")
    private String cartProductOption2;

    @Column(name="cart_product_price")
    private Double cartProductPrice;

    @Column(name="cart_product_count")
    private int   cartProductCount;

    @Column(name="cart_product_img")
    private String cartProductImg;

}
