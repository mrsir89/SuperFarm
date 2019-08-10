package com.project.superfarm.repository;

import com.project.superfarm.entity.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value="SELECT * FROM product JOIN product_type USING(product_type_code) " +
            "WHERE lower_code=?",nativeQuery = true)
    List<Product> loadLowerCategory(Long lower_code);

    @Query(value="SELECT * FROM product JOIN product_type USING(product_type_code) " +
            "WHERE upper_code=?",nativeQuery = true)
    List<Product> loadUpperCategory(Long upper_code);

    // LIKE 사용 하여 이름 검색
    @Query(value="SELECT * FROM product JOIN product_type USING(product_type_code) " +
            "WHERE product_name LIKE %?%",nativeQuery = true)
    List<Product> loadFindItemName(String itemName);


}
