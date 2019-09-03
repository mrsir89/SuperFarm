package com.project.superfarm.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * Todo : client에서 받아올 객체 정의 해야한다.  우선은 title만 받아옴
 *
 * @deprecated  status 상태를 확인하고 임시로 만든것인지 확인 status가 short 이면 임시저장
 */
@Data
public class ProductBoardCreateModel implements Serializable {

    // model안의 상태를 확인하여 임시저장과 구별
    private String status;

    private Long productBoardNum;

    private Integer upperCode;

    private Integer lowerCode;

    private String productBoardTitle;

    private String productBoardThumbnail;

    private Double productBoardDeliveryPrice;

    private Integer productBoardBest;

    private String productBoardTag;

    private String productBoardLink;

    private String productBoardCommon;

    private String productBoardDetail;

    private String productBoardBottom;

    private String productEditContent;

    private Date productAddDate;

    private String productBoardDeleted;

}
