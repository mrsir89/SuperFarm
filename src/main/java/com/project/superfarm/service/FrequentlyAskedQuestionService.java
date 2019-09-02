package com.project.superfarm.service;


import com.project.superfarm.entity.board.FrequentlyAskedQuestionBoard;
import com.project.superfarm.repository.boardRepository.FrequentlyAskedQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FrequentlyAskedQuestionService {

    @Autowired
    private FrequentlyAskedQuestionRepository frequentlyAskedQuestionRepository;

    public Page<FrequentlyAskedQuestionBoard> loadAllFrequentlyAskedQuestionBoard(Pageable pageable) {

        return frequentlyAskedQuestionRepository.findAllByFaqDeletedOrderByFaqBoardNum("false", pageable);
    }


}