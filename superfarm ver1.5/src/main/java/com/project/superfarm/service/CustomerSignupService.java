package com.project.superfarm.service;



import com.project.superfarm.entity.User.Customer;
import com.project.superfarm.entity.User.Roles;
import com.project.superfarm.entity.User.Users;
import com.project.superfarm.model.SignupCustomer;
import com.project.superfarm.repository.userRepository.CustomerRepository;
import com.project.superfarm.repository.userRepository.RolesRepository;
import com.project.superfarm.repository.userRepository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    회원 가입/ 회원 조회 해서 return / 회원 정보 수정(특정 수정으로 나눌것이냐 아니면 한번에 다 할것이냐) /
    회원 삭제는 없다.(단지 숨길뿐)/ 회원 정보 추가(coupon) /
 */

@Service
public class CustomerSignupService {


    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Transactional
    public String singupCutomer(SignupCustomer signupCustomer){

        Users<Customer> signupUser = new Users();
        Customer customer = new Customer();

        Long userNum = null;

        if(signupCustomer !=null) {

        Roles roles = rolesRepository.findByName("ROLE_CUSTOMER");
            //User 분리
            signupUser = signupCustomer.getUser();
            // 권한 설정
            signupUser.setRoles(Stream.of(roles)
                    .collect(Collectors.toSet()));

            usersRepository.save(signupUser);
            Optional<Users> tmpuser = usersRepository.findByUserId(signupUser.getUserId());

            Users user = tmpuser.get();
            userNum = user.getUserNum();

            customer = signupCustomer.getCustomer();
            customer.setUserNum(userNum);

            customerRepository.save(customer);
            return "Success";
        }
        else{
                return "Fail";
        }

    }

    /*
        email 중복체크
 */
    public String overlapEmail(String email) {

        String overlapEmail = usersRepository.overlapEmail(email);

        return columnCheck(email);
    }

    public String overlapId(String id){

        String overlapId = usersRepository.overlapId(id);

        return columnCheck(id);
    }
    private String columnCheck(String checkColumn){

        if(checkColumn ==null){
            return "Fail";
        }else
            return "Successs";
    }



}
