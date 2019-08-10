package com.project.superfarm.service;


import com.project.superfarm.entity.Board.ReviewBoard;
import com.project.superfarm.repository.boardRepository.ReviewBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewBoardService {


    @Autowired
    private ReviewBoardRepository reviewBoardRepository;

    public List<ReviewBoard> loadAllReviewBoard() {

        List<ReviewBoard> reviewBoards = reviewBoardRepository.findAll();
        if(reviewBoards.size()==0){
            return null;
        }else{
            return reviewBoards;
        }
    }

    public List<ReviewBoard> loadFromProductBoard(Long productBoardCode) {

        List<ReviewBoard> reviewBoards = reviewBoardRepository.findAllByProductBoardNum(productBoardCode);

        if(reviewBoards.size()==0){
            return null;
        }else{
            return reviewBoards;
        }
    }

    public List<ReviewBoard> writeReviewBoard(ReviewBoard reviewBoard){

        reviewBoardRepository.save(reviewBoard);
        return reviewBoardRepository.findAllByProductBoardNum(reviewBoard.getProductBoardNum());

    }

    public String deleteReviewBoard(Long reviewBoardNum){

        reviewBoardRepository.deleted(reviewBoardNum);

        String checkDelete = reviewBoardRepository.findbyDeleted(reviewBoardNum);

        if(checkDelete.equals("true")){
            return "Suceess";
        }else{
            return "Fail";
        }
    }

}
