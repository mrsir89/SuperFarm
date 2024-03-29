package com.project.superfarm.repository.boardRepository;

import com.project.superfarm.entity.board.ProductBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductBoardRepository extends JpaRepository<ProductBoard,Long> {


        @Query(value = "SELECT * FROM product_board  JOIN product USING(product_code) " +
                "WHERE product_board_deleted ='false' AND product_Board_title LIKE %:name% OR product_board_tag LIKE %:name% OR " +
                "product.product_name LIKE %:name% " ,nativeQuery = true)
        List<ProductBoard> loadSearchKeyword(@Param("name") String name);

        List<ProductBoard> findAllByUpperCodeAndProductBoardDeleted(int upperCode,String deleted);

        List<ProductBoard> findAllByLowerCodeAndProductBoardDeleted(int lowerCode,String deleted);




}
