package com.project.superfarm.entity.board;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="review_board")
@DynamicInsert
@DynamicUpdate @Setter @Getter
public class ReviewBoard implements Serializable {



    @Id
    @Column(name="review_board_num",updatable = false,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewBoardNum;

    @Column(name="product_board_num",nullable = false)
    private Long productBoardNum;

    @Column(name="customer_id",nullable = false)
    private String customerId;

    @Column(name="review_board_img")
    private String reviewBoardImg;

    @Column(name="review_board_title",nullable = false)
    private String reviewBoardTitle;

    @Column(name="review_board_content")
    private String reviewBoardContent;

    @Column(name="review_board_rating")
    private Integer reviewBoardRating;

    @Column(name="review_board_regdate")
    @CreationTimestamp
    private Date reviewBoardRegDate;

    @Column(name="review_board_deleted")
    private String reviewBoardDeleted;

    @Column(name="review_board_delete_date")
    private Date reviewBoardDeleteDate;


}
