package com.project.superfarm.repository.Orders;

import com.project.superfarm.entity.orders.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping,Long> {
    
}
