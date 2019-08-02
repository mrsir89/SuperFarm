package com.project.superfarm.model;

import com.project.superfarm.entity.Customer;
import com.project.superfarm.entity.CustomerContact;

import java.io.Serializable;

public class Signup implements Serializable {

    private String id;

    private String password;

    private String name;

    private int birthday;

    private String gender;

    private SignupContact signupContact;





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public SignupContact getSignupContact() {
        return signupContact;
    }

    public void setSignupContact(SignupContact signupContact) {
        this.signupContact = signupContact;
    }

    public Customer getcustomer(){

        Customer customer = new Customer();
        customer.setCid(id);
        customer.setC_pwd(password);
        customer.setC_name(name);
        customer.setC_gender(gender);
        return customer;
    }


}
