package com.project.superfarm.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="testtime")
public class TestTime {

    @Id
    @Column(name ="test_pk",nullable = false,updatable = false)
    private String test_pk;

    @Temporal(TemporalType.TIMESTAMP)
    private Date test_date;

    public String getTest_pk() {
        return test_pk;
    }

    public void setTest_pk(String test_pk) {
        this.test_pk = test_pk;
    }

    public Date getTest_date() {
        return test_date;
    }

    public void setTest_date(Date test_date) {
        this.test_date = test_date;
    }
}
