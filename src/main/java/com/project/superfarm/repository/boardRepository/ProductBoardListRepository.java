package com.project.superfarm.repository.boardRepository;

import com.project.superfarm.entity.board.ProductBoard;
import com.project.superfarm.entity.board.ProductBoardList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductBoardListRepository extends JpaRepository<ProductBoardList, Long> {


    @Query(value = "SELECT board.product_board_num, board.product_board_title,board.upper_code,board.lower_code,board.product_board_thumbnail,board.product_board_best ," +
            "(SELECT MIN(product.product_price) FROM product_board innerboard LEFT JOIN product USING(product_board_num)  " +
            "WHERE board.product_board_num = product.product_board_num ) product_price " +
            "FROM product_board board " +
            "LEFT JOIN product USING(product_board_num) " +
            "WHERE board.lower_code = ?1 AND product_board_deleted =false " +
            "GROUP BY product_board_num ", nativeQuery = true)
    List<ProductBoardList> findByLowerProductBoard(Integer lower);


    @Query(value = "SELECT board.product_board_num, board.product_board_title,board.upper_code,board.lower_code,board.product_board_thumbnail,board.product_board_best ," +
            "(SELECT MIN(product.product_price) FROM product_board innerboard LEFT JOIN product USING(product_board_num)  " +
            "WHERE board.product_board_num = product.product_board_num ) product_price " +
            "FROM product_board board " +
            "LEFT JOIN product USING(product_board_num) " +
            "WHERE board.upper_code =?1 AND product_board_deleted =false " +
            "GROUP BY product_board_num ", nativeQuery = true)
    List<ProductBoardList> findByUpperProductBoard(Integer upper);

    // 검색어 로 찾기
    @Query(value = "SELECT board.product_board_num, board.product_board_title,board.upper_code,board.lower_code,board.product_board_thumbnail,board.product_board_best , " +
            "(SELECT MIN(product.product_price) FROM product_board innerboard LEFT JOIN product USING(product_board_num)  " +
            "WHERE board.product_board_num = product.product_board_num ) product_price " +
            "FROM product_board board " +
            "LEFT JOIN product USING(product_board_num) " +
            "WHERE product_board_deleted = false AND product_Board_title LIKE %:search% OR product_board_tag LIKE %:search% OR " +
            "product.product_name LIKE %:search% " +
            "GROUP BY product_board_num ", nativeQuery = true)
    List<ProductBoardList> findBySearchProductBoard(@Param("search") String search);


    // 관리자용 전체 불러 오기
    @Query(value = "SELECT board.product_board_num, board.product_board_title,board.upper_code,board.lower_code,board.product_board_thumbnail,board.product_board_best ," +
            "(SELECT MIN(product.product_price) FROM product_board innerboard LEFT JOIN product USING(product_board_num)  " +
            "WHERE board.product_board_num = product.product_board_num ) product_price " +
            "FROM product_board board " +
            "LEFT JOIN product USING(product_board_num) " +
            "GROUP BY product_board_num ", nativeQuery = true)
    List<ProductBoardList> findByAllProductBoard();




    @Query(value = "SELECT board.product_board_num, board.product_board_title,board.upper_code,board.lower_code,board.product_board_thumbnail,board.product_board_best ," +
            "(SELECT MIN(product.product_price) FROM product_board innerboard LEFT JOIN product USING(product_board_num)  " +
            "WHERE board.product_board_num = product.product_board_num ) product_price " +
            "FROM product_board board " +
            "LEFT JOIN product USING(product_board_num) " +
            "WHERE product_board_best >0 AND product_board_deleted ='false' "+
            "GROUP BY product_board_num ", nativeQuery = true)
    List<ProductBoardList> findByBestProduct();


    @Query(value = "SELECT board.product_board_num, board.product_board_title,board.upper_code,board.lower_code,board.product_board_thumbnail,board.product_board_best ," +
            "(SELECT MIN(product.product_price) FROM product_board innerboard LEFT JOIN product USING(product_board_num)  " +
            "WHERE board.product_board_num = product.product_board_num ) product_price " +
            "FROM product_board board " +
            "LEFT JOIN product USING(product_board_num) " +
            "WHERE product_board_best >0 AND board.lower_code=?1 AND product_board_deleted ='false' "+
            "GROUP BY product_board_num ", nativeQuery = true)
    List<ProductBoardList> findByLowerBestProduct(Integer lower);

    ///테스트용
    @Query(value = "SELECT board.product_board_num, board.product_board_title,board.upper_code,board.lower_code,board.product_board_thumbnail,board.product_board_best ," +
            "(SELECT MIN(product.product_price) FROM product_board innerboard LEFT JOIN product USING(product_board_num)  " +
            "WHERE board.product_board_num = product.product_board_num ) product_price " +
            "FROM product_board board " +
            "LEFT JOIN product USING(product_board_num) " +
            "GROUP BY product_board_num ", nativeQuery = true)
    List<ProductBoardList> findByAllTest();


}
