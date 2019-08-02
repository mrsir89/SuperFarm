package com.project.superfarm.entity;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer_contact")
@DynamicInsert
@DynamicUpdate
public class CustomerContact implements Serializable {

    @Id
    @Column(name ="c_num")
    private Long cnum;

    @Column(name="c_phone")
    private int c_phone;

    @Column(name="c_addr")
    private String  c_addr;

    @Column(name="c_email")
    private String  c_email;


    public Long getCnum() {
        return cnum;
    }

    public void setCnum(Long cnum)
    {
        this.cnum = cnum;
    }

    public int getC_phone() {
        return c_phone;
    }

    public void setC_phone(int c_phone) {
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

}
