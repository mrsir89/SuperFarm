package com.project.superfarm.entity.board;

import com.project.superfarm.entity.product.Product;
import com.project.superfarm.model.ProductListModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@DynamicInsert
@DynamicUpdate
@ToString
@Entity
@Table(name = "product_board")
@Getter
@Setter
public class ProductBoardList {


    @Id
    @Column(name = "product_board_num")
    private Long productBoardNum;

    @Column(name = "upper_code")
    private Integer upperCode;

    @Column(name = "lower_code")
    private Integer lowerCode;

    @Column(name = "product_board_title")
    private String productBoardTitle;

    @Column(name = "product_board_thumbnail")
    private String productBoardThumbnail;

     @Column(name = "product_board_best")
    private Integer productBoardBest;

    @Column(name = "product_price")
    private Double productPrice;


    @Transient
    public ProductListModel getProductListModel() {

        ProductListModel productListModel = new ProductListModel(
                productBoardNum, productBoardTitle, upperCode, lowerCode,
                productPrice, productBoardThumbnail, productBoardBest
        );

        return productListModel;

    }

}

