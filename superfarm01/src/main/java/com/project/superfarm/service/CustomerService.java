package com.project.superfarm.service;


import com.project.superfarm.entity.Customer;
import com.project.superfarm.entity.CustomerContact;
import com.project.superfarm.repository.CustomerContactRepository;
import com.project.superfarm.repository.CustomerLogedRepository;
import com.project.superfarm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/*
    회원 가입/ 회원 조회 해서 return / 회원 정보 수정(특정 수정으로 나눌것이냐 아니면 한번에 다 할것이냐) /
    회원 삭제는 없다.(단지 숨길뿐)/ 회원 정보 추가(coupon) /
 */

@Service
public class CustomerService {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerContactRepository customerContactRepository;

    @Autowired
    private CustomerLogedRepository customerLogedRepository;

    // 유저 ID를 가지고 검색
    public Optional<Customer> findCustomer(Long c_num){

        Optional<Customer>
                customer = customerRepository.findById(c_num);
        return customer;
    }
    public Optional<Customer> userallInfo(Long c_num){

        Optional<Customer> customer = customerRepository.findById(c_num);
        if(customer==null) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"찾으시는 ID가 없습니다.");
        }
            Optional<CustomerContact> customerContact = customerContactRepository.findById(c_num);
            customer.get().setContact(customerContact.get());
        return customer;
    }

    public void updateCustomerPoint(Long c_num,int point){

        customerRepository.updatePoint(point,c_num);

    }
    public void customerEdit(Long c_num,Customer customer) {

       customerRepository.save(customer);

    }

    public void connectInsertLog(Long c_num){



    }




}
