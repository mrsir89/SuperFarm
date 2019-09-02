package com.project.superfarm.repository;

import com.project.superfarm.entity.product.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    List<Cart> findAllByUserNum(Long userNum);

//    void deleteAllByUserNum(Long userNum);

    @Modifying
    @Query(value="UPDATE cart SET cart_product_count = ?1 WHERE cart_num =?2",nativeQuery = true)
    int cartProductCountUpdate(int count ,Long cartProductNum);

    int deleteAllByUserNum(Long userNum);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM cart WHERE user_num=?1 ",nativeQuery = true)
    void delAllUserCart(Long userNum);
}
