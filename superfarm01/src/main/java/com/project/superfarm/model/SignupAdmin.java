package com.project.superfarm.model;

import com.project.superfarm.entity.MarketAdmin;
import sun.nio.cs.ext.MacArabic;

import java.io.Serializable;

public class SignupAdmin implements Serializable {

    private String id;
    private String pwd;
    private String name;
    private String phone;
    private String businessnum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBusinessnum() {
        return businessnum;
    }

    public void setBusinessnum(String businessnum) {
        this.businessnum = businessnum;
    }


    public MarketAdmin getAdmin(){
        MarketAdmin marketAdmin = new MarketAdmin();

        marketAdmin.setAdminid(id);
        marketAdmin.setAdminpwd(pwd);
        marketAdmin.setAdmin_name(name);
        marketAdmin.setAdmin_phone(Integer.parseInt(phone));
        marketAdmin.setAdmin_business_num(Integer.parseInt(businessnum));

        return marketAdmin;
    }
}
