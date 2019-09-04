package com.project.superfarm.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReturnModel implements Serializable {


    public ReturnModel(String number){
        this.number = number;
    }
    private String number;

}
