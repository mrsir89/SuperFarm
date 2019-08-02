package com.project.superfarm.model;


import com.project.superfarm.entity.CustomerContact;

public class SignupContact {

    private Long cnum;

    private String c_phone;

    private String  c_addr;

    private String  c_email;


    public Long getCnum() {
        return cnum;
    }

    public void setCnum(Long cnum) {
        this.cnum = cnum;
    }

    public String getC_phone() {
        return c_phone;
    }

    public void setC_phone(String c_phone) {
        this.c_phone = c_phone;
    }

    public String getC_addr() {
        return c_addr;
    }

    public void setC_addr(String c_addr) {
        this.c_addr = c_addr;
    }

    public String getC_email() {
        return c_email;
    }

    public void setC_email(String c_email) {
        this.c_email = c_email;
    }

    public CustomerContact getContact(){

        CustomerContact contact = new CustomerContact();
        contact.setCnum(cnum);
        contact.setC_addr(c_addr);
        contact.setC_email(c_email);
        contact.setC_phone(Integer.parseInt(c_phone));

        return contact;

    }

}
