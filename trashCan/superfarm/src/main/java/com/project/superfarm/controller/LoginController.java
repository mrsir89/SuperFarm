package com.project.superfarm.controller;


import com.project.superfarm.entity.Customer;
import com.project.superfarm.util.PrincipalUtil;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class LoginController {


    @PreAuthorize("hasAnyAuthority('guest','PRODUCT_READ')")
    @RequestMapping(
            value = "/me",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public Customer me(Principal principal) {

        return (Customer) PrincipalUtil.from(principal);
    }

}
