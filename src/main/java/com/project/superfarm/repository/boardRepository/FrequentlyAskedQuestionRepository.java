package com.project.superfarm.repository.boardRepository;


import com.project.superfarm.entity.board.FrequentlyAskedQuestionBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrequentlyAskedQuestionRepository extends JpaRepository<FrequentlyAskedQuestionBoard, Long> {

    Page<FrequentlyAskedQuestionBoard> findAllByFaqDeletedOrderByFaqBoardNum(String deleted, Pageable pageable);
}
