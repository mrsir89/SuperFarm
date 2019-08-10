package com.project.superfarm.repository.boardRepository;

import com.project.superfarm.entity.Board.ReviewBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewBoardRepository extends JpaRepository<ReviewBoard,Long> {

    @Query(value="SELECT * FROM review_board WHERE review_board_deleted = false AND " +
            "product_board_num=?1 ",nativeQuery = true)
    List<ReviewBoard> findAllByProductBoardNum(Long productBoardCode);


    @Query(value = "UPDATE review_board set review_board_deleted ='false' " +
            "review_board_delete_date =NOW() WHERE review_board_num=?1 ",nativeQuery = true)
    String deleted(Long reviewBoardNum);

    @Query(value="SELECT review_board_deleted FROM review_board WHERE review_board_num =?1",nativeQuery = true)
    String findbyDeleted(Long reviewBoardNum);

}
