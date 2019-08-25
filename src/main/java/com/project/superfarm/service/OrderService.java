package com.project.superfarm.service;


import com.project.superfarm.entity.orders.OrderItems;
import com.project.superfarm.entity.orders.Orders;
import com.project.superfarm.entity.orders.OrdersShipping;
import com.project.superfarm.entity.orders.Shipping;
import com.project.superfarm.model.OrderModel;
import com.project.superfarm.repository.Orders.OrderItemsRepository;
import com.project.superfarm.repository.Orders.OrdersRepository;
import com.project.superfarm.repository.Orders.OrdersShippingRepository;
import com.project.superfarm.repository.Orders.ShippingRepository;
import com.project.superfarm.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {


    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private OrdersShippingRepository ordersShippingRepository;


    // 동시에 저장
    public List<OrdersShipping> checkoutOrder(OrderModel orderModel){

        // 초기 셋팅
        Long shippingNum;
        Long orderItemNum;
        Long ordersNum;
        //return할 객체 생성
        List<OrdersShipping> ordersShippingList = new ArrayList<>();
        // 빈객체인지 체크
        if(!ObjectUtils.isEmpty(orderModel)) {

            Orders order = orderModel.getOrder();
            Shipping shippings = orderModel.getShipping();
            List<OrderItems> orderItems = orderModel.getOrderItemsList();


            Orders returnOrder = ordersRepository.save(order);

            ordersNum = returnOrder.getOrderNum();

            for (int i = 0; i < orderItems.size(); i++) {

                // 초기화 작업 매 반복문 실행시 기존에 있는 자료를 초기화시키기위해
                Shipping returnShipping = new Shipping();
                OrderItems returnOrderItems = new OrderItems();
                OrdersShipping ordersShipping = new OrdersShipping();
                OrdersShipping returnOrderShipping = new OrdersShipping();

                returnShipping = shippingRepository.save(shippings);
                returnOrderItems = orderItemsRepository.save((orderItems.get(i)));

                shippingNum = returnShipping.getShippingNum();
                orderItemNum = returnOrderItems.getOrderItemNum();

                if(shippingNum !=null && orderItemNum !=null && ordersNum !=null){

                    ordersShipping.setOrderNum(ordersNum);
                    ordersShipping.setOrderItemNum(orderItemNum);
                    ordersShipping.setShippingNum(shippingNum);

                    returnOrderShipping =
                            ordersShippingRepository.save(ordersShipping);

                    ordersShippingList.add(ordersShipping);
                }
            }
        }
            return ordersShippingList;

    }




}
