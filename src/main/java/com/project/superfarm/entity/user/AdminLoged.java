package com.project.superfarm.entity.user;

import javax.persistence.Column;
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
