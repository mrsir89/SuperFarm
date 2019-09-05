package com.project.superfarm.model;

import com.project.superfarm.entity.product.Product;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MyOrderListModel {

    private Long orderNum;

    private Long userNum;

    private String productBoardTitle;

    private String productBoardThumnail;

    private String option1;

    private String option2;

    private Double orderTotalPrice;

    private Date orderSellDate;

    private List<Product> product;


}
