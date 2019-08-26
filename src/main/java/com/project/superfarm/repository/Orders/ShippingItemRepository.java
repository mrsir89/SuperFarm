package com.project.superfarm.repository.Orders;

import com.project.superfarm.entity.orders.ShippingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingItemRepository extends JpaRepository<ShippingItem,Long> {
}
