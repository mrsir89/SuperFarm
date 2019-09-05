package com.project.superfarm.repository.Orders;


import com.project.superfarm.entity.orders.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {

    List<Orders> findAllByUserNum(Long userNum);
}
