package com.project.superfarm.repository.boardRepository;


import com.project.superfarm.entity.Board.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer,Long> {

    List<QuestionAnswer> findAllByQuestionBoardNumAndAnswerDeleted(Long QuestionBoardNum,String deleted);

}
