package com.project.superfarm.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Setter
@Getter
public class CustomerEdit implements Serializable {


    private Long userNum;

    private String userId;

    private String userPassword;

    private String userEmail;

    private String customerPhone;

    private String customerAddr;




}
