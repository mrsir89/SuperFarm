package com.project.superfarm.model;


import com.project.superfarm.entity.product.Product;

import java.util.List;

public class ProductDetails {

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

    private String productAddDate;

    private String productBoardDeleted;

    private List<Product> productList;

    private String productTypeName;


    public Long getProductBoardNum() {
        return productBoardNum;
    }

    public void setProductBoardNum(Long productBoardNum) {
        this.productBoardNum = productBoardNum;
    }

    public Integer getUpperCode() {
        return upperCode;
    }

    public void setUpperCode(Integer upperCode) {
        this.upperCode = upperCode;
    }

    public Integer getLowerCode() {
        return lowerCode;
    }

    public void setLowerCode(Integer lowerCode) {
        this.lowerCode = lowerCode;
    }

    public String getProductBoardTitle() {
        return productBoardTitle;
    }

    public void setProductBoardTitle(String productBoardTitle) {
        this.productBoardTitle = productBoardTitle;
    }

    public String getProductBoardThumbnail() {
        return productBoardThumbnail;
    }

    public void setProductBoardThumbnail(String productBoardThumbnail) {
        this.productBoardThumbnail = productBoardThumbnail;
    }

    public Double getProductBoardDeliveryPrice() {
        return productBoardDeliveryPrice;
    }

    public void setProductBoardDeliveryPrice(Double productBoardDeliveryPrice) {
        this.productBoardDeliveryPrice = productBoardDeliveryPrice;
    }

    public Integer getProductBoardBest() {
        return productBoardBest;
    }

    public void setProductBoardBest(Integer productBoardBest) {
        this.productBoardBest = productBoardBest;
    }

    public String getProductBoardTag() {
        return productBoardTag;
    }

    public void setProductBoardTag(String productBoardTag) {
        this.productBoardTag = productBoardTag;
    }

    public String getProductBoardLink() {
        return productBoardLink;
    }

    public void setProductBoardLink(String productBoardLink) {
        this.productBoardLink = productBoardLink;
    }

    public String getProductBoardCommon() {
        return productBoardCommon;
    }

    public void setProductBoardCommon(String productBoardCommon) {
        this.productBoardCommon = productBoardCommon;
    }

    public String getProductBoardDetail() {
        return productBoardDetail;
    }

    public void setProductBoardDetail(String productBoardDetail) {
        this.productBoardDetail = productBoardDetail;
    }

    public String getProductBoardBottom() {
        return productBoardBottom;
    }

    public void setProductBoardBottom(String productBoardBottom) {
        this.productBoardBottom = productBoardBottom;
    }

    public String getProductEditContent() {
        return productEditContent;
    }

    public void setProductEditContent(String productEditContent) {
        this.productEditContent = productEditContent;
    }

    public String getProductAddDate() {
        return productAddDate;
    }

    public void setProductAddDate(String productAddDate) {
        this.productAddDate = productAddDate;
    }

    public String getProductBoardDeleted() {
        return productBoardDeleted;
    }

    public void setProductBoardDeleted(String productBoardDeleted) {
        this.productBoardDeleted = productBoardDeleted;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }
}


