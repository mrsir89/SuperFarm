package com.project.superfarm.service;

import com.project.superfarm.entity.MarketAdmin;
import com.project.superfarm.entity.Roles;
import com.project.superfarm.model.SignupAdmin;
import com.project.superfarm.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupAdminService {

    @Autowired
    private RolesRepository rolesRepository;

    public MarketAdmin createAdmin(SignupAdmin signupAdmin){

        String authority = signupAdmin.getAuthority();

        Roles role = rolesRepository.findByName(authority);



            return null;
    }


}
