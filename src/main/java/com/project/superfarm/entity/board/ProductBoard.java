package com.project.superfarm.entity.board;

import com.project.superfarm.entity.product.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@DynamicInsert
@DynamicUpdate
@ToString
@Entity
@Table(name = "product_board")
@Getter
@Setter
public class ProductBoard implements Serializable {


    @Id
    @Column(name = "product_board_num", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productBoardNum;

    @Column(name = "upper_code")
    private Integer upperCode;

    @Column(name = "lower_code")
    private Integer lowerCode;

    @Column(name = "product_board_title")
    private String productBoardTitle;

    @Column(name = "product_board_thumbnail")
    private String productBoardThumbnail;

    @Column(name = "product_board_deliveryprice")
    private Double productBoardDeliveryPrice;

    @Column(name = "product_board_best")
    private Integer productBoardBest;

    @Column(name = "product_board_tag")
    private String productBoardTag;

    @Column(name = "product_board_link")
    private String productBoardLink;

    @Column(name = "product_board_common")
    private String productBoardCommon;

    @Column(name = "product_board_detail")
    private String productBoardDetail;

    @Column(name = "product_board_bottom")
    private String productBoardBottom;

    @Column(name = "product_edit_content")
    private String productEditContent;

    @Column(name = "product_add_date")
    private Date productAddDate;

    @Column(name = "product_board_deleted")
    private String productBoardDeleted;

    @Transient
    private String productTypeName;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_board_num", referencedColumnName = "product_board_num")
    private List<Product> productList;


}
