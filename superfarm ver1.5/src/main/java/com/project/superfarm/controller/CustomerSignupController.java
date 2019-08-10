package com.project.superfarm.controller;

import com.project.superfarm.model.SignupCustomer;
import com.project.superfarm.service.CustomerSignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/*
    @Customer 회원가입 API
    joinCreate 회원가입

    @overlapName 아이디 중복체크 return boolean
    @overlapEmail email 중복체크 return boolean
    @overlapPhone phone 중복체크 return boolean


 */

@RestController
@RequestMapping("/signup")
public class CustomerSignupController {


    @Autowired
    private CustomerSignupService customerSignupService;

    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE
            })
    public String joinCreate(@RequestBody SignupCustomer signupCustomer) {
        System.out.println("=============회원 가입 시작 ============");

        return customerSignupService.singupCutomer(signupCustomer);
    }



    /*
        email 중복 체크
        중복된 email이 없으면 true, email이 있으면 false
     */
    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(path="/emailCheck",
            method = {RequestMethod.GET,RequestMethod.POST},
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE })
    public String overlapEmail(@RequestParam String email ){

        return  customerSignupService.overlapEmail(email);
    }


    /*
    email 중복 체크
    중복된 email이 없으면 true, email이 있으면 false
     */
    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(path="/idCheck",
            method = {RequestMethod.GET,RequestMethod.POST},
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE })
    public String overlapId(@RequestParam String id ){

        return  customerSignupService.overlapId(id);
    }



//
//    @PreAuthorize("hasRole('GUEST')")
//    @RequestMapping(path="/phoneCheck",
//            method = {RequestMethod.GET,RequestMethod.POST},
//            produces = {
//                    MediaType.APPLICATION_JSON_UTF8_VALUE,
//                    MediaType.APPLICATION_ATOM_XML_VALUE })
//    public boolean overlapPhoneNumber(@RequestParam String phoneNumber ){
//
//        return  signupService.overlapPhone(phoneNumber);
//    }



}
