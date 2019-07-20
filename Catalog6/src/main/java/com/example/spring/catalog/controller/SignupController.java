package com.example.spring.catalog.controller;

import com.example.spring.catalog.entity.User;
import com.example.spring.catalog.model.Signup;
import com.example.spring.catalog.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private SignupService signupService;

    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            })
    public User signup(@RequestBody Signup signup) {
        return signupService.registerMerchant(signup);
    }
}
