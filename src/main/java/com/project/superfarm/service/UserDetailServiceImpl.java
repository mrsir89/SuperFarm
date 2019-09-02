package com.project.superfarm.service;


import com.project.superfarm.entity.user.Admin;
import com.project.superfarm.entity.user.Customer;
import com.project.superfarm.entity.user.Users;
import com.project.superfarm.model.CustomerEdit;
import com.project.superfarm.repository.userRepository.AdminsRepository;
import com.project.superfarm.repository.userRepository.CustomerRepository;
import com.project.superfarm.repository.userRepository.UsersRepository;
import com.project.superfarm.util.ExceptionList.UrlNotFountException;
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
    // 회원정보 수정
    public Users edit(CustomerEdit customerEdit) {


        // 수정할 객체 생성 초기화
        Users<Customer> editUser = new Users<Customer>();
        Customer editCustomer = new Customer();

        if(customerEdit.getUserNum() != null){
            Long userNum = customerEdit.getUserNum();

            Optional<Users> user = usersRepository.findById(userNum);
            Optional<Customer> customer = customerRepository.findById(userNum);

            if(user.isPresent()){
                editUser = user.get();
                editUser.setEditUser(customerEdit);

                editCustomer = customer.get();
                editCustomer.setCustomerEdit(customerEdit);
            }

            editUser  =usersRepository.save(editUser);
            editCustomer = customerRepository.save(editCustomer);

            editUser.setPosition(editCustomer);

            return editUser;
        }else
            throw new UrlNotFountException();



    }
}
