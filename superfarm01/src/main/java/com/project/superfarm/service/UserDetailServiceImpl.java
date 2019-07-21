package com.project.superfarm.service;

import com.project.superfarm.entity.Customer;
import com.project.superfarm.entity.CustomerContact;
import com.project.superfarm.repository.CustomerContactRepository;
import com.project.superfarm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerContactRepository customerContactRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByCid(username);
        Optional<CustomerContact> contact = customerContactRepository.findById(customer.getCnum());
        customer.setContact(contact.get());
        if(customer==null){
            throw new UsernameNotFoundException(username);
        }
        return customer;
    }
}
