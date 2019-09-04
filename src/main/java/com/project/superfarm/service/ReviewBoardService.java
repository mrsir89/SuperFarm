package com.project.superfarm.service;


import com.project.superfarm.entity.board.ReviewBoard;

import com.project.superfarm.repository.boardRepository.ReviewBoardRepository;
import com.project.superfarm.util.ExceptionList.UrlNotFountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
            return String.valueOf(reviewBoardNum);
        }else{
            throw new UrlNotFountException();
        }
    }

    public void reviewBoardImgUpload(Long reviewBoarNum, String replaceImgUrl){

        System.out.println("reveiwBoard에 img 넣기 시작 ");
        Optional<ReviewBoard> reviewBoard = reviewBoardRepository.findById(reviewBoarNum);
        reviewBoard.get().setReviewBoardImg(replaceImgUrl);
        reviewBoardRepository.save(reviewBoard.get());

    }

}
