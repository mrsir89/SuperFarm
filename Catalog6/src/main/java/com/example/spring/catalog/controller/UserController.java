package com.example.spring.catalog.controller;

import com.example.spring.catalog.entity.User;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @RequestMapping(
            value = "/me",
            method = RequestMethod.GET,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public User me(Principal principal) {
        User user = null;
        try {
            OAuth2Authentication authentication
                    = (OAuth2Authentication) principal;
            UsernamePasswordAuthenticationToken token
                    = (UsernamePasswordAuthenticationToken) authentication.getUserAuthentication();
            user = (User) token.getPrincipal();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }
}
