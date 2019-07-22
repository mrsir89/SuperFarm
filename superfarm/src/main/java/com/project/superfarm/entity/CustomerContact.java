package com.project.superfarm.entity;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "customer_contact")
@DynamicInsert
@DynamicUpdate
public class CustomerContact {

    @Id
    @Column(name="c_num",updatable = false,nullable = false)
//    @OneToOne
   // @JoinColumn(name="customer",referencedColumnName = "c_num")
    private Long c_num;

    @Column(name="c_phone")
    private int c_phone;

    @Column(name="c_addr")
    private String  c_addr;

    @Column(name="c_email")
    private String  c_email;



}
