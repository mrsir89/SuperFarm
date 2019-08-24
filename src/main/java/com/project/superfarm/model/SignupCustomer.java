package com.project.superfarm.model;


import com.project.superfarm.entity.user.Customer;
import com.project.superfarm.entity.user.Users;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@ToString
public class SignupCustomer implements Serializable {

    private String userId;

    private String userPassword;

    private String userName;

    private String userType;

    private String userEmail;

    private Date customerBirth;

//    private String customerGender;

    private String customerPhone;

    private String customerAddr;


    private Users user;

    public Users getUser(){

        Users user = new Users();

        user.setUserId(userId);
        user.setUserPassword(userPassword);
        user.setUserName(userName);
        user.setUserEmail(userEmail);
        user.setUserType("customer");

        return user;
    }

    private Customer customer;

    public Customer getCustomer(){

        Customer customer = new Customer();
//        customer.setCustomerGender(customerGender);
        customer.setCustomerBirth(customerBirth);
        customer.setCustomerAddr(customerAddr);
        customer.setCustomerPhone(customerPhone);
        customer.setCustomerCoupon(null);
        customer.setCustomerGrade("일반");
        customer.setCustomerPoint(0);

        return customer;
    }


}
