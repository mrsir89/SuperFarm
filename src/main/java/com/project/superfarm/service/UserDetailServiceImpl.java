package com.project.superfarm.service;


import com.project.superfarm.entity.user.Admin;
import com.project.superfarm.entity.user.Customer;
import com.project.superfarm.entity.user.Users;
import com.project.superfarm.model.CustomerEdit;
import com.project.superfarm.repository.userRepository.AdminsRepository;
import com.project.superfarm.repository.userRepository.CustomerRepository;
import com.project.superfarm.repository.userRepository.UsersRepository;
import com.project.superfarm.util.UrlNotFountException;
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

        System.out.println(username + " username");
        Optional<Users> usersOptional =  usersRepository.findByUserId(username);
        System.out.println("loadUserByUsername 실행");
      //  if(usersOptional.isPresent()) {
            // 접속 기록 Log 남기기
//            usersRepository.createLog(username);
            if(usersOptional.get().getUserType().equals("customer")) {
                System.out.println("customer 실행 확인 ");

                Users<Customer> user = usersOptional.get();
                Optional<Customer> customerOptional = customerRepository.findById(user.getUserNum());
                    user.setPosition(customerOptional.get());
                System.out.println(user.toString());
                return  user;

            }else if(usersOptional.get().getUserType().equals("admin")){
                Users<Admin> user = usersOptional.get();
                Optional<Admin> adminOptional = adminsRepository.findById(user.getUserNum());
                Admin admin = adminOptional.get();
                user.setPosition(admin);
                return user;
            }

     //   }else
            //return throw new UsernameNotFoundException(username);
            return usersOptional.get();
    }

    public Users edit(CustomerEdit customerEdit) {

        Customer customer = new Customer();
        Users<Customer> user = new Users<Customer>();

        Users<Customer> editUser = new Users<Customer>();
        Customer editCustomer = new Customer();

        if(customerEdit != null){
            user = customerEdit.getUsers();
            customer = customerEdit.getCustomer();

            editUser  =usersRepository.save(user);
            editCustomer = customerRepository.save(customer);

            editUser.setPosition(editCustomer);
            return editUser;
        }else
            throw new UrlNotFountException();



    }
}
