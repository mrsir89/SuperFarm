package com.project.superfarm.repository;

import com.project.superfarm.entity.product.Product_Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<Product_Type,Long> {

}
