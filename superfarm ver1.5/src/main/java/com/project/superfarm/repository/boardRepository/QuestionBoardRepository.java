package com.project.superfarm.repository.boardRepository;

import com.project.superfarm.entity.Board.QuestionBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionBoardRepository extends JpaRepository<QuestionBoard,Long> {




    List<QuestionBoard> findAllByProductBoardNumAndQuestionBoardDeleted(Long productNum,String deleted);

    List<QuestionBoard> findAllByUserIdAndQuestionBoardDeleted(String userId,String deleted);



}
