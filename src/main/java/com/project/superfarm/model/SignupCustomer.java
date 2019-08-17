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

    private String customerGender;

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
        customer.setCustomerGender(customerGender);
        customer.setCustomerBirth(customerBirth);
        customer.setCustomerAddr(customerAddr);
        customer.setCustomerPhone(customerPhone);
        customer.setCustomerCoupon(null);
        customer.setCustomerGrade("일반");
        customer.setCustomerPoint(0);

        return customer;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public void setUser(Users user) {
        this.user = user;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }



}
