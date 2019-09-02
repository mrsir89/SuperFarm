package com.project.superfarm.controller;

import com.project.superfarm.entity.user.Customer;
import com.project.superfarm.entity.user.Users;
import com.project.superfarm.model.SignupCustomer;
import com.project.superfarm.service.CustomerSignupService;
import com.project.superfarm.util.ExceptionList.UrlNotFountException;
import com.project.superfarm.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
         @apiNote      :  SignUp API (회원가입)
         @UrL /signup : /, /emailCheck, /idCheck

         @brief       : / 회원가입  \n
                      : /emailCheck 중복 Email 확인 \n
                      : /idCheck    중복 Id 확인 \n

 **/
@RestController
@RequestMapping("/signup")
public class CustomerSignupController {


    @Autowired
    private CustomerSignupService customerSignupService;



    /**
     * @Url        : /signup/
     * @apiNote    : Customer 회원 가입 / 권한(Guest)
     * @see        : Java : Users.java, cutomer.java
     *               DB   : Users,customer
     * @param      : Json
     *              	"userId":"tester01",
                      	"userPassword":"Test1234",
                      	"userName":"테스터01",
                      	"userType":"customer",
                      	"userEmail":"test01@Test.com",
                      	"customerBirth":"2019-08-15",
                      	"customerGender":"남",
                      	"customerPhone":"010-2222-2222",
                      	"customerAddr":"서울입니다."
     * @return     : Json Users.java
                     "userNum": 10007,
                      "userId": "tester07",
                      "userPassword": "Test1234",
                      "userType": "customer",
                      "userRegday": "2019-08-13T06:01:13.466+0000",
                      "userEmail": "test07@Test.com",
                      "userLastConnect": "2019-08-13T06:01:13.466+0000",
                      "position": {
                          "userNum": 10007,
                          "customer_birth": "2019-08-15T00:00:00.000+0000",
                          "customer_gender": "남",
                          "customerGrade": "일반",
                          "customerPoint": 0,
                          "customerCoupon": null,
                          "customerPhone": "010-2222-2222",
                          "customerAddr": "서울입니다."
                      },
     *
     *
    */
    @PreAuthorize("hasAnyRole('GUEST','CUSTOMER','ADMIN')")
    @RequestMapping(
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE

            })
    public Users<Customer> joinCreate(@RequestBody SignupCustomer signupCustomer) {
        System.out.println(signupCustomer.toString());
        if(!ObjectUtils.isEmpty(signupCustomer)) {
            return customerSignupService.singupCutomer(signupCustomer);

        }else
            throw new UrlNotFountException();
    }


    /**
     * Todo      : 프론트중복 처리 String ->  status 에러로 변경 하기 !완료!
     * @apiNote  : param으로 들어 오는 email 중복처리
     * @Url      : /signup/emailCheck
     * @param    : String email
     * @return   : Success( 200 ok ) / NotFoundException
     */
    @PreAuthorize("hasAnyRole('GUEST','CUSTOMER','ADMIN')")
    @RequestMapping(value="/emailCheck",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public String overlapEmail(@RequestParam(name="email") String email ){

        if(!ObjectUtils.isEmpty(email)) {
            return customerSignupService.overlapEmail(email);

        }else
            throw new UrlNotFountException();
    }



    /**
     * Todo : 프론트중복 처리 String ->  status 에러로 변경 하기 !완료!
     * @apiNote  : param으로 들어 오는 id 중복처리
     * @Url      : /signup/idCheck
     * @param    : string id
     * @return   : Success( 200 ok ) / NotFoundException
     */
    @PreAuthorize("hasAnyRole('GUEST','CUSTOMER','ADMIN')")
    @RequestMapping(value="/idCheck",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public String overlapId(@RequestParam(name="id") String id ){

        if(!ObjectUtils.isEmpty(id)) {
            return customerSignupService.overlapId(id);

        }else
            throw new UrlNotFountException();
    }

    /**
     * @apiNote FAKE API 리액트에서 비동기를 동기로 처리 하기 위해서 만듬
     */
    @PostMapping(value="/asyncAction")
    public void fakeAsyncAction(){

    }



}
