package com.project.superfarm.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class ProductListModel implements Serializable {


    public ProductListModel() {

    }

    public ProductListModel(Long productBoardNum, String productBoardTitle, Integer upperCode, Integer lowerCode, Double productPrice,
                            String productBoardThumbnail, Integer productBoardBest) {

        this.productBoardNum = productBoardNum;
        this.productBoardTitle = productBoardTitle;
        this.upperCode = upperCode;
        this.lowerCode = lowerCode;
        this.productBoardBest = productBoardBest;
        this.productPrice = productPrice;
        this.productBoardThumbnail = productBoardThumbnail;

    }

    private Long productBoardNum;

    private String productBoardTitle;

    private Integer upperCode;

    private Integer lowerCode;

    private Double productPrice;

    private String productBoardThumbnail;

    private Integer productBoardBest;


}
