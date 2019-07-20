package com.example.spring.catalog.model;

import java.io.Serializable;

public class Phone implements Serializable {

    private PhoneType phoneType;

    private String phoneNumber;

    private String phoneAreaCode;

    private String phoneCountryCode;

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneAreaCode() {
        return phoneAreaCode;
    }

    public void setPhoneAreaCode(String phoneAreaCode) {
        this.phoneAreaCode = phoneAreaCode;
    }

    public String getPhoneCountryCode() {
        return phoneCountryCode;
    }

    public void setPhoneCountryCode(String phoneCountryCode) {
        this.phoneCountryCode = phoneCountryCode;
    }
}
