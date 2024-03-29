package com.project.superfarm.service;


import com.project.superfarm.entity.user.Admin;
import com.project.superfarm.entity.user.Customer;
import com.project.superfarm.entity.user.Users;
import com.project.superfarm.repository.userRepository.AdminsRepository;
import com.project.superfarm.repository.userRepository.CustomerRepository;
import com.project.superfarm.repository.userRepository.UsersRepository;
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
    private UsersRepository usersRepository;

    @Autowired
    private AdminsRepository adminsRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<Users> usersOptional =  usersRepository.findByUserId(username);

        if(usersOptional.isPresent()) {
            // 접속 기록 Log 남기기
//            usersRepository.createLog(username);
            if(usersOptional.get().getUserType().equals("customer")) {
                Users<Customer> user = usersOptional.get();
                Optional<Customer> customerOptional = customerRepository.findById(user.getUserNum());
                    user.setPosition(customerOptional.get());

                return  user;

            }else if(usersOptional.get().getUserType().equals("admin")){
                Users<Admin> user = usersOptional.get();
                Optional<Admin> adminOptional = adminsRepository.findById(user.getUserNum());
                Admin admin = adminOptional.get();
                user.setPosition(admin);

                return user;
            }
            else{
                return null;
            }

        }else
                throw new UsernameNotFoundException(username);

    }
}
