package com.project.superfarm.service;


import com.project.superfarm.entity.orders.*;
import com.project.superfarm.entity.product.Product;
import com.project.superfarm.model.OrderModel;
import com.project.superfarm.repository.Orders.*;
import com.project.superfarm.repository.ProductRepository;
import com.project.superfarm.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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


    /**
     * @param orderModel
     * @return
     * @apiNote order 생성시  shipping shipping_item orders order_item 과
     * order_shipping 까지 같이 생성 해야 한다.
     */
    @Transactional
    public Orders checkoutOrder(OrderModel orderModel) {

        // 초기 셋팅
        Long shippingNum = 0L;
        Long orderItemNum = 0L;
        Long ordersNum = 0L;

        if (orderModel != null) {

            // order번호를 생성 하기 위해 먼저 저장
            Orders orders = orderModel.getOrder();
            Orders returnOrders = ordersRepository.save(orders);

            // 이번 주문에 대한 orderNum을 정한다.
            ordersNum = returnOrders.getOrderNum();

            int orderItemCount = orderModel.getOrderItemsList().size();

            //orderItem 개수에 따른 저장 시작
            for (int i = 0; i < orderItemCount; i++) {

                // 초기화 임시로 저장값을 담을 객체와 리턴 받아 pk 키를 받을 객체를 생성
                Shipping tmpShipping = new Shipping();
                Shipping returnShipping = new Shipping();

                OrderItems tmpOrderItem = new OrderItems();
                OrderItems returnOrderItem = new OrderItems();

                ShippingItem tmpShippingItem = new ShippingItem();

                OrdersShipping tmpOrdersShipping = new OrdersShipping();
                OrdersShipping returnOrderShipping = new OrdersShipping();
                // order item 저장 / 저장 전 ordersnum 삽입 하고 저장
                tmpOrderItem = orderModel.getOrderItemsList().get(i);
                tmpOrderItem.setOrderNum(ordersNum);
                returnOrderItem = orderItemsRepository.save(tmpOrderItem);
                orderItemNum = returnOrderItem.getOrderItemNum();

                // shipping 저장  / 저장전 ordersnum 삽입 하고 저장
                tmpShipping = orderModel.getShipping();
                tmpShipping.setOrderNum(ordersNum);
                returnShipping = shippingRepository.save(tmpShipping);
                shippingNum = returnShipping.getShippingNum();

                // shipping item setting
                tmpShippingItem = shippingItemSet(returnOrderItem,shippingNum);

                // 예외처리 해야 함
                if (tmpShippingItem == null) {
                    return null;
                }
                // 3개의 값을 동시에 넣어 준다.
                tmpOrdersShipping.setOrderNum(ordersNum);
                tmpOrdersShipping.setShippingNum(shippingNum);
                tmpOrdersShipping.setOrderItemNum(orderItemNum);

                returnOrderShipping = ordersShippingRepository.save(tmpOrdersShipping);

                // 여기도 예외처리 해야함
                if (returnOrderShipping == null) {
                    return null;
                }

            }

            Optional<Orders> resultOrder = ordersRepository.findById(ordersNum);
            if (resultOrder.isPresent()) {
                Orders result = resultOrder.get();
                return result;
            } else {

                return null;
            }
        } else {

            return null;
        }

    }



    private ShippingItem shippingItemSet(OrderItems orderItems,Long shippingNum) {

        ShippingItem shippingItem = new ShippingItem();

        Long productCode = orderItems.getProductCode();
        Optional<Product> optionalProduct = productRepository.findById(productCode);

        if (optionalProduct.isPresent()) {

            Product product = optionalProduct.get();
            shippingItem.setProductCode(product.getProductCode());
            shippingItem.setProductName(product.getProductName());
            shippingItem.setProductOption1(product.getProductOption1());
            shippingItem.setProductOption2(product.getProductOption2());
            shippingItem.setProductPrice(product.getProductPrice());
            shippingItem.setShippingNum(shippingNum);

        }
        return shippingItemRepository.save(shippingItem);
    }


    public List<Orders> loadOrderList(Long num) {

        List<Orders> ordersList = ordersRepository.findAllByUserNum(num);

        return ordersList;
    }
}
