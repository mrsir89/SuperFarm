package com.project.superfarm.model;

import com.project.superfarm.entity.user.Customer;
import com.project.superfarm.entity.user.Users;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ToString
@Setter
@Getter
public class CustomerEdit implements Serializable {


    private Long userNum;

    private String userId;

    private String userPassword;

    private String userEmail;

    private Date customerBirth;

    private String customerGender;

    private String customerPhone;

    private String customerAddr;

    private Users users;

    private Customer customer;

    public Users getUsers(){
        Users user = new Users();
        user.setUserId(userId);
        user.setUserNum(userNum);
        user.setUserPassword(userPassword);
        user.setUserEmail(userEmail);
        return user;
    }

    public Customer getCustomer(){
        Customer customer = new Customer();
        customer.setUserNum(userNum);
        customer.setCustomerBirth(customerBirth);
        customer.setCustomerGender(customerGender);
        customer.setCustomerPhone(customerPhone);
        customer.setCustomerAddr(customerAddr);
        return customer;
    }


}
