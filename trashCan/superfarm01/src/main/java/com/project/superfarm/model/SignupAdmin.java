package com.project.superfarm.model;

import java.io.Serializable;

public class SignupAdmin implements Serializable {

    private String id;
    private String pwd;
    private String name;
    private String authority;
    private int phone;
    private int businessnum;

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

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getBusinessnum() {
        return businessnum;
    }

    public void setBusinessnum(int businessnum) {
        this.businessnum = businessnum;
    }


    public SignupAdmin getAdmin(){
        SignupAdmin signupAdmin = new SignupAdmin();
        signupAdmin.setId(id);
        signupAdmin.setName(name);
        signupAdmin.setPwd(pwd);
        signupAdmin.setAuthority(authority);
        signupAdmin.setBusinessnum(businessnum);
        signupAdmin.setPhone(phone);

        return signupAdmin;
    }
}
