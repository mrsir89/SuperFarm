package com.project.superfarm.entity.board;

import com.project.superfarm.model.ProductListModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 *
 * //: 읽기 전용으로 선언해야 한다.(찾아보기)
 *
 *   product_board table에서 product_price를 서브쿼리로 가지고오는 과정에서
*     기존 product_board를 이용시에는 사용하지 않는 product_price라는 컬럼의 존재 오류때문에
*     추가로 생성한 Entity
*     이Entity의 용도는 보여주기의 product_list의 값만 리턴 해주는용도
*
 */

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

