package com.project.superfarm.model.payment;

import lombok.Data;

import java.io.Serializable;

@Data
public class KakaopayResult implements Serializable {

    private String pg_token;

    private String tid;

    private String partner_order_id;

    private String partner_user_id;

    private String total_amount;
}
