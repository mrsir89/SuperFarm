package com.project.superfarm.entity.user;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="admins")
@DynamicUpdate
@DynamicInsert
@Setter
@Getter
@ToString
public class Admin implements Serializable {

    @Id
    @Column(name="user_num",nullable = false,updatable = false,insertable = false)
    private Long user_num;

    @Column(name="admin_authority",nullable = false)
    private String adminAuthority;

    @Column(name="admin_phone",nullable = false)
    private String adminPhone;

    @Column(name="admin_business_num",nullable = false)
    private String adminBusinessNum;

}
