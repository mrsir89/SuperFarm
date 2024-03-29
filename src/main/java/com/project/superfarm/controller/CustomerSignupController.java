package com.project.superfarm.controller;

import com.project.superfarm.entity.user.Customer;
import com.project.superfarm.entity.user.Users;
import com.project.superfarm.model.SignupCustomer;
import com.project.superfarm.service.CustomerSignupService;
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
    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE

            })
    public Users<Customer> joinCreate(@RequestBody SignupCustomer signupCustomer) {
        System.out.println("=============회원 가입 시작 ============");

        return customerSignupService.singupCutomer(signupCustomer);
    }


    /**
     * Todo      : 프론트중복 처리 String ->  status 에러로 변경 하기
     * @apiNote  : param으로 들어 오는 email 중복처리
     * @Url      : /signup/emailCheck
     * @param    : String email
     * @return   : True / false
     */
    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(path="/emailCheck",
            method = {RequestMethod.GET,RequestMethod.POST},
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE })
    public String overlapEmail(@RequestParam String email ){

        return  customerSignupService.overlapEmail(email);
    }



    /**
     * Todo : 프론트중복 처리 String ->  status 에러로 변경 하기
     * @apiNote  : param으로 들어 오는 id 중복처리
     * @Url      : /signup/idCheck
     * @param    : string id
     * @return   : True / false
     */
    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(path="/idCheck",
            method = {RequestMethod.GET,RequestMethod.POST},
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE })
    public String overlapId(@RequestParam String id ){

        return  customerSignupService.overlapId(id);
    }



//
//    @PreAuthorize("hasRole('GUEST')")
//    @RequestMapping(path="/phoneCheck",
//            method = {RequestMethod.GET,RequestMethod.POST},
//            produces = {
//                    MediaType.APPLICATION_JSON_UTF8_VALUE,
//                    MediaType.APPLICATION_ATOM_XML_VALUE })
//    public boolean overlapPhoneNumber(@RequestParam String phoneNumber ){
//
//        return  signupService.overlapPhone(phoneNumber);
//    }



}
