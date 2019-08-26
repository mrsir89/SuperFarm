package com.project.superfarm.repository.Orders;

import com.project.superfarm.entity.orders.OrdersShipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersShippingRepository extends JpaRepository<OrdersShipping,Long> {



}
