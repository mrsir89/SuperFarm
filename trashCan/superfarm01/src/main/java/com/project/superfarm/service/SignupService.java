package com.project.superfarm.service;


import com.project.superfarm.entity.Customer;
import com.project.superfarm.entity.CustomerContact;
import com.project.superfarm.entity.Roles;
import com.project.superfarm.model.Signup;
import com.project.superfarm.model.SignupContact;
import com.project.superfarm.repository.CustomerContactRepository;
import com.project.superfarm.repository.CustomerRepository;
import com.project.superfarm.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SignupService  {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerContactRepository customerContactRepository;

    @Autowired
    private RolesRepository rolesRepository;


    public Customer createCustomer(Signup signup){

        Customer customer = signup.getcustomer();

        SignupContact contact = signup.getSignupContact();


        Roles roles = rolesRepository.findByName("ROLE_CUSTOMER");
        customer.setRoles(Stream.of(roles)
                .collect(Collectors.toSet()));
        customerRepository.save(customer);


        Long tmp = customerRepository.findByCid(customer.getCid()).getCnum();
        contact.setCnum(tmp);
        CustomerContact customerContact  = contact.getContact();
        customerContactRepository.save(customerContact);
        customer.setContact(customerContact);

        return customer;
    }


}
