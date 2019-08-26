package com.project.superfarm.service;


import com.project.superfarm.entity.orders.*;
import com.project.superfarm.entity.product.Product;
import com.project.superfarm.model.OrderModel;
import com.project.superfarm.repository.Orders.*;
import com.project.superfarm.repository.ProductRepository;
import com.project.superfarm.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private ShippingItemRepository shippingItemRepository;

    @Autowired
    private ProductRepository productRepository;

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
            Shipping shippings = new Shipping();
            orderModel.getShipping();
            List<OrderItems> orderItems = orderModel.getOrderItemsList();

            Orders returnOrder = ordersRepository.save(order);

            ordersNum = returnOrder.getOrderNum();

            for (int i = 0; i < orderItems.size(); i++) {

                // 초기화 작업 매 반복문 실행시 기존에 있는 자료를 초기화시키기위해

                OrderItems returnOrderItems = new OrderItems();
                Shipping returnShipping = new Shipping();


                OrdersShipping ordersShipping = new OrdersShipping();
                OrdersShipping returnOrderShipping = new OrdersShipping();

                // 새로운 객체의 새로운 값을 저장 하기 위해서 다시 한번 저장
                // 다시 저장하지 않을시 pk가 늘어 나지 않는다.
                shippings = orderModel.getShipping();


                returnShipping = shippingRepository.save(shippings);
                returnOrderItems = orderItemsRepository.save((orderItems.get(i)));
                System.out.println(returnOrderItems.toString());
                ShippingItem shippingItem = shippingItemSet(returnOrderItems);

                shippingNum = returnShipping.getShippingNum();
                orderItemNum = returnOrderItems.getOrderItemNum();
                shippingItem.setShippingNum(shippingNum);

                shippingItemRepository.save(shippingItem);


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

    public Orders loadOrderList(Long num){

        Optional<Orders> ordersList = ordersRepository.findById(num);
        if(ordersList.isPresent()){
            return ordersList.get();
        }
        else{
            return null;
        }
    }

    private ShippingItem shippingItemSet(OrderItems orderItems){

        ShippingItem shippingItem = new ShippingItem();

        Long productCode = orderItems.getProductCode();
        Optional<Product> optionalProduct = productRepository.findById(productCode);

        if(optionalProduct.isPresent()) {

         Product product = optionalProduct.get();
            shippingItem.setProductCode(product.getProductCode());
            shippingItem.setProductName(product.getProductName());
            shippingItem.setProductOption1(product.getProductOption1());
            shippingItem.setProductOption2(product.getProductOption2());
            shippingItem.setProductPrice(product.getProductPrice());

        }
        return shippingItem;
    }




}
