package com.project.superfarm.model.payment;


import lombok.Data;

/**
 * 카카오페이 관련 return Model
 */
@Data
public class OrderSend {

    private String item_name;

    private String partner_order_id;

    private String partner_user_id;

    private String quantity;

    private String total_amount;

    private String tax_free_amount;


}
