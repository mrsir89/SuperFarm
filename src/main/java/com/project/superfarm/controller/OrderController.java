package com.project.superfarm.controller;

import com.project.superfarm.entity.orders.Orders;
import com.project.superfarm.model.OrderModel;
import com.project.superfarm.service.OrderService;
import com.project.superfarm.util.ExceptionList.UrlNotFountException;
import com.project.superfarm.util.isNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;

    // 테스트용으로 ADMIN도 넣어 둠
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_ADMIN')")
    @RequestMapping(
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public Orders checkout(@RequestBody OrderModel orderModel) {

        System.out.println(orderModel.toString());
        System.out.println(orderModel.getOrderItemsList().toString());

        Orders result = orderService.checkoutOrder(orderModel);
        return result;

    }


    // 테스트 용으로 ADMIN도 넣어 둠
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @Transactional
    @RequestMapping(path = "/user",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public List<Orders> loadOrderList(@RequestParam(name = "num") String strNum) {

        if (isNumber.isStringLong(strNum)) {
            Long num = Long.parseLong(strNum);
            System.out.println("order List Num " + num);
            return orderService.loadOrderList(num);

        } else {
            throw new UrlNotFountException();
        }
    }
}
