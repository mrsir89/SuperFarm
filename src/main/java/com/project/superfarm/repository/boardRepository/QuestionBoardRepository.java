package com.project.superfarm.repository.boardRepository;

import com.project.superfarm.entity.board.QuestionAnswer;
import com.project.superfarm.entity.board.QuestionBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionBoardRepository extends JpaRepository<QuestionBoard,Long> {

//    @Query(value="SELECT *  FROM question_board a LEFT JOIN question_answer b " +
//            "ON a.question_board_num = b.question_board_num WHERE a.user_id = ?1 AND " +
//            " a.question_board_deleted = 'false' ORDER BY a.question_board_num DESC",nativeQuery = true)
    Page<QuestionBoard> findAllByUserIdAndQuestionBoardDeleted(String userId,String delete,Pageable pageable);

//    @Query(value="SELECT *  FROM question_board a LEFT JOIN question_answer b " +
//            "ON a.question_board_num = b.question_board_num WHERE a.product_board_num=?1 AND " +
//            "a.question_board_deleted = 'false' ORDER BY a.question_board_num DESC",nativeQuery = true)
    Page<QuestionBoard> findAllByProductBoardNumAndQuestionBoardDeleted(Long productNum,String delete,Pageable pageable);

    @Modifying
    @Query(value ="UPDATE question_board SET question_board_deleted = 'true',question_board_delete_date = NOW() " +
            " WHERE question_board_num =?1 ",nativeQuery = true)
    int deleteQuestionBoard(Long answerNum);

    Page<QuestionBoard> findAllByProductBoardNumAndQuestionBoardDeletedAndQuestionAnswer_AnswerDeleted(
            Long productNum,String questionDeleted,String answerDeleted,Pageable pageable);

    @Modifying
    @Query(value="UPDATE question_board SET question_board_status ='true' WHERE question_board_num=?1 ",nativeQuery = true)
    int updateQuestionBoardStatus(Long questionBoardNum);
}
