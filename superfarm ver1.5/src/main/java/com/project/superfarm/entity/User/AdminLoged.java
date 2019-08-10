package com.project.superfarm.entity.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

//@Entity
//@Table(name="admin_loged")
//@DynamicInsert @DynamicUpdate
//@ToString @Setter @Getter
public class AdminLoged {

    @Column(name = "admin_id")
    private String adminId;

    @Column(name="admin_loged")
    private Date adminLoged;


}
