package com.project.superfarm.model.payment;


import lombok.Data;

import java.util.Date;

@Data
public class KaKaoPayReadyVO {

    //response
    private String tid, next_redirect_pc_url;
    private Date created_at;
}
