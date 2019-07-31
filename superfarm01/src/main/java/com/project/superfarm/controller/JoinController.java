package com.project.superfarm.controller;


import com.project.superfarm.entity.Customer;
import com.project.superfarm.entity.MarketAdmin;
import com.project.superfarm.model.Signup;
import com.project.superfarm.model.SignupAdmin;
import com.project.superfarm.service.CustomerService;
import com.project.superfarm.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
public class JoinController {


    @Autowired
    private CustomerService customerService;

    @Autowired
    private SignupService signupService;

    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping( value="signupAdmin",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public MarketAdmin joinAdmin(@RequestBody SignupAdmin signupAdmin){

        return signupService.createAdmin(signupAdmin);

    }

    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping( value="signup",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public Customer joinCreate(@RequestBody Signup signup){

        return signupService.createCustomer(signup);

    }


}
