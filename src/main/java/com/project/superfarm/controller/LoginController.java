package com.project.superfarm.controller;

import com.project.superfarm.entity.user.Customer;
import com.project.superfarm.entity.user.Users;
import com.project.superfarm.model.CustomerEdit;
import com.project.superfarm.service.UserDetailServiceImpl;
import com.project.superfarm.util.ExceptionList.UrlNotFountException;
import com.project.superfarm.util.PrincipalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @apiNote : (로그인)
 * @Url /user  : /me
 * @brief : / 로그인 시 회원 정보 리턴
 **/


@RestController
@RequestMapping("/users")
public class LoginController {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    /**
     * @param principal 토큰에 저장된 정보
     * @return "userNum": 1,
     * "userId": "tester01",
     * "userPassword": "Test1234",
     * "userType": "customer",
     * "userRegday": "1999-01-01T00:00:00.000+0000",
     * "userEmail": "test01@test.com",
     * "userLastConnect": "2019-08-13T00:00:00.000+0000",
     * "position": {
     * "userNum": 1,
     * "customer_birth": "2019-08-15T00:00:00.000+0000",
     * "customer_gender": "남",
     * "customerGrade": "일반",
     * "customerPoint": 0,
     * "customerCoupon": null,
     * "customerPhone": "010-1111-2222",
     * "customerAddr": "서울에 삼니다용"
     * },
     * @Url : /users/me
     * @see : jave :  customer.java,Cart.java
     * DB   : Users , Customer, Cart
     */
    @PreAuthorize("hasAnyRole('GUEST','CUSTOMER','ADMIN')")
    @RequestMapping(
            value = "/me",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            })
    public Users me(Principal principal) {

        System.out.println(principal.getName() + "      -<< name");

        return (Users) userDetailService.loadUserByUsername(PrincipalUtil.from(principal).getUsername());

    }


    /**
     * @param customerEdit
     * @return
     * @apiNote 회원정보 수정
     */
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @RequestMapping(value = "/edit",
            method = RequestMethod.PATCH,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public Users<Customer> customerEdit(@RequestBody CustomerEdit customerEdit) {

        System.out.println(customerEdit.toString());
        if (customerEdit != null) {
            Users<Customer> customerUser = userDetailService.edit(customerEdit);

            return customerUser;

        } else
            throw new UrlNotFountException();
    }

}
