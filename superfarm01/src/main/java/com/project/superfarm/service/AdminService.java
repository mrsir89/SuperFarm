package com.project.superfarm.service;

import com.project.superfarm.entity.MarketAdmin;
import com.project.superfarm.repository.CustomerRepository;
import com.project.superfarm.repository.MarketAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdminService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MarketAdminRepository marketAdminRepository;


}
