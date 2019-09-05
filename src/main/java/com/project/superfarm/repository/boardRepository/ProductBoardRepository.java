package com.project.superfarm.repository.boardRepository;

import com.project.superfarm.entity.board.ProductBoard;
import com.project.superfarm.model.ProductListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductBoardRepository extends JpaRepository<ProductBoard, Long> {


    @Query(value = "SELECT * FROM product_board  JOIN product USING(product_code) " +
            "WHERE product_board_deleted ='false' AND product_Board_title LIKE %:name% OR product_board_tag LIKE %:name% OR " +
            "product.product_name LIKE %:name% ", nativeQuery = true)
    List<ProductBoard> loadSearchKeyword(@Param("name") String name);


    List<ProductBoard> findAllByUpperCodeAndProductBoardDeleted(int upperCode, String deleted);


    List<ProductBoard> findAllByLowerCodeAndProductBoardDeleted(int lowerCode, String deleted);

    @Query(value = "SELECT * FROM product_board WHERE product_board_best >0 AND product_board_deleted ='false' ORDER BY product_board_best ASC", nativeQuery = true)
    List<ProductBoard> findMainProduct();


    ///test
    @Query(value = "SELECT board.*," +
            "(SELECT MIN(product.product_price) FROM product_board innerboard LEFT JOIN product USING(product_board_num)  " +
            "WHERE board.product_board_num = product.product_board_num ) product_price " +
            "FROM product_board board " +
            "LEFT JOIN product USING(product_board_num) " +
            "GROUP BY product_board_num ", nativeQuery = true)
    List<ProductBoard> findByAllTest();

    @Query(value="SELECT product_board_thumbnail FROM product_board WHERE product_board_num=?1 ",nativeQuery = true)
    String loadThumbnail(Long productBoardNum);
}
