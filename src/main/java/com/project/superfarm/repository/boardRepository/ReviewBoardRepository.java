package com.project.superfarm.repository.boardRepository;

import com.project.superfarm.entity.board.ReviewBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewBoardRepository extends JpaRepository<ReviewBoard,Long> {

    Page<ReviewBoard> findAllByReviewBoardDeleted(String delete,Pageable pageable);

    Page<ReviewBoard> findAllByProductBoardNumAndReviewBoardDeleted(Long productBoardCode, String delete, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE review_board set review_board_deleted ='true' , " +
            "review_board_delete_date = NOW() WHERE review_board_num=?1 ",nativeQuery = true)
    int deleted(Long reviewBoardNum);


}
