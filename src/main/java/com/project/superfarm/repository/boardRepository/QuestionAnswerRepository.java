package com.project.superfarm.repository.boardRepository;


import com.project.superfarm.entity.board.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer,Long> {

    @Modifying
    @Query(value = "UPDATE question_answer SET answer_date = NOW(), answer_content = :content " +
            "WHERE answer_num = :answerNum " ,nativeQuery = true)
    int updateAnswer(@Param("content") String content, @Param("answerNum") Long answerNum);

    @Modifying
    @Query(value = "UPDATE question_answer SET answer_deleted = 'true', answer_board_delete_date = NOW()" +
            " WHERE answer_num = ?1",nativeQuery = true)
    int deleteAnswer(Long answerNum);
}