package com.project.superfarm.controller;


import com.project.superfarm.entity.MarketAdmin;
import com.project.superfarm.model.SignupAdmin;
import com.project.superfarm.service.SignupAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinAdminController {

    @Autowired
    private SignupAdminService signupAdminService;

    @RequestMapping(value="admin",
                    method= RequestMethod.POST,
                    produces = {
                            MediaType.APPLICATION_JSON_UTF8_VALUE,
                            MediaType.APPLICATION_ATOM_XML_VALUE
                    })
    public MarketAdmin createAdmin(@RequestBody SignupAdmin signupAdmin){

       MarketAdmin marketAdmin= signupAdminService.createAdmin(signupAdmin);


        return marketAdmin;
    }


}
