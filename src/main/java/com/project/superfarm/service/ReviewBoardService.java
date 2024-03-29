package com.project.superfarm.service;


import com.project.superfarm.entity.board.ReviewBoard;

import com.project.superfarm.repository.boardRepository.ReviewBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ReviewBoardService {


    @Autowired
    private ReviewBoardRepository reviewBoardRepository;

    public Page<ReviewBoard> loadAllReviewBoard(Pageable pageable) {

        Page<ReviewBoard> reviewBoards = reviewBoardRepository.findAllByReviewBoardDeleted("false",pageable);
        if(reviewBoards.isEmpty()){
            return null;
        }else{
            return reviewBoards;
        }
    }

    public Page<ReviewBoard> loadFromProductBoard(Long productBoardCode, Pageable pageable) {

        Page<ReviewBoard> reviewBoards
                = reviewBoardRepository.findAllByProductBoardNumAndReviewBoardDeleted(productBoardCode,"false",pageable);

        if(reviewBoards.isEmpty()){
            return null;
        }else{
            return reviewBoards;
        }
    }

    @Transactional
    public ReviewBoard writeReviewBoard(ReviewBoard reviewBoard){

        return reviewBoardRepository.save(reviewBoard);

    }

    @Transactional
    public String deleteReviewBoard(Long reviewBoardNum){

        int result = reviewBoardRepository.deleted(reviewBoardNum);

        if(result==1){
            return "Suceess";
        }else{
            return "Fail";
        }
    }

}
