package com.project.superfarm.model.payment;


import lombok.Data;

import java.io.Serializable;

@Data
public class AmountVO implements Serializable {

    private Integer total, tax_free, vat, point, discount;
}
