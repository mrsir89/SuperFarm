package com.project.superfarm.entity;


import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer_loged")
public class CustomerLoged {


    @Id
    @Column(name = "c_num",nullable = false,updatable = false)
    private Long c_num;


    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date c_Lastlog;


}
