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

    @Transient
    public void setCustomerEdit(CustomerEdit customerEdit){
        this.userNum = customerEdit.getUserNum();
        this.customerPhone = customerEdit.getCustomerPhone();
        this.customerAddr = customerEdit.getCustomerAddr();
    }


}
