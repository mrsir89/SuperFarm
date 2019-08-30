package com.project.superfarm.controller;

import com.project.superfarm.entity.orders.Orders;
import com.project.superfarm.model.OrderModel;
import com.project.superfarm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @Transactional
    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
                        MediaType.APPLICATION_ATOM_XML_VALUE}
    )
    public Orders checkout(@RequestBody OrderModel orderModel){

        System.out.println(orderModel.toString());
        System.out.println(orderModel.getOrderItemsList().toString());

        return orderService.checkoutOrder(orderModel);
    }

    @Transactional
    @GetMapping(path = "/user",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Orders loadOrderList(@RequestParam(name="num")Long num){
        System.out.println("order List Num "+num);

        return orderService.loadOrderList(num);
    }
}
