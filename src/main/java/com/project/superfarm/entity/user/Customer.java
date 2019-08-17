package com.project.superfarm.entity.user;

import com.project.superfarm.model.CouponJSON;
import com.project.superfarm.model.CustomerEdit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.io.Serializable;

import java.util.Date;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "customer")
@Setter@Getter@ToString
public class Customer  implements Serializable {

    @Id
    @Column(name = "user_num", nullable = false)
    private Long userNum;

    @Column(name="customer_birth")
    private Date customerBirth;

    @Column(name="customer_gender")
    private String customerGender;

    @Column(name="customer_grade")
    private String customerGrade;

    @Column(name="customer_point")
    private Integer customerPoint;

    @Column(name="customer_coupon")
    private CouponJSON customerCoupon;

    @Column(name="customer_phone")
    private String customerPhone;

    @Column(name="customer_addr")
    private String customerAddr;


    /////////////////////////////////////////////////////////////////////
    public Long getUserNum() {
        return userNum;
    }

    @Transient
    public void setCustomerEdit(CustomerEdit customerEdit){
        this.userNum = customerEdit.getUserNum();
        this.customerPhone = customerEdit.getCustomerPhone();
        this.customerAddr = customerEdit.getCustomerAddr();
    }



    public void setUserNum(Long userNum) {
        this.userNum = userNum;
    }

    public Date getCustomer_birth() {
        return customerBirth;
    }

    public void setCustomer_birth(Date customer_birth) {
        this.customerBirth = customer_birth;
    }

//    public String getCustomer_gender() {
//        return customer_gender;
//    }
//
//    public void setCustomer_gender(String customer_gender) {
//        this.customer_gender = customer_gender;
//    }

    public String getCustomerGrade() {
        return customerGrade;
    }

    public void setCustomerGrade(String customerGrade) {
        this.customerGrade = customerGrade;
    }

    public Integer getCustomerPoint() {
        return customerPoint;
    }

    public void setCustomerPoint(Integer customerPoint) {
        this.customerPoint = customerPoint;
    }

    public CouponJSON getCustomerCoupon() {
        return customerCoupon;
    }

    public void setCustomerCoupon(CouponJSON customerCoupon) {
        this.customerCoupon = customerCoupon;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddr() {
        return customerAddr;
    }

    public void setCustomerAddr(String customerAddr) {
        this.customerAddr = customerAddr;
    }


    public Date getCustomerBirth() {
        return customerBirth;
    }

    public void setCustomerBirth(Date customerBirth) {
        this.customerBirth = customerBirth;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }
}
