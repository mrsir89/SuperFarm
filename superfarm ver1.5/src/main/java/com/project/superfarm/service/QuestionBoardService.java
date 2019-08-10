package com.project.superfarm.service;

import com.project.superfarm.entity.Board.QuestionAnswer;
import com.project.superfarm.entity.Board.QuestionBoard;
import com.project.superfarm.repository.boardRepository.QuestionAnswerRepository;
import com.project.superfarm.repository.boardRepository.QuestionBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class QuestionBoardService {



    @Autowired
    private QuestionBoardRepository questionBoardRepository;


    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;



    public List<QuestionBoard> loadFromProductBoard(Long productBoardNum){

        List<QuestionBoard> questionBoards =
                questionBoardRepository.findAllByProductBoardNumAndQuestionBoardDeleted(productBoardNum,"false");

        if(questionBoards.size()==0){
            return null;
        }else{
            return questionBoards;
        }
    }


    // UserNum으로 검색 하면서 deleted가 false인 것들만
    public List<QuestionBoard> loadFromUserId(String userId) {

        List<QuestionBoard> questionBoards =
                questionBoardRepository.findAllByUserIdAndQuestionBoardDeleted(userId,"false");

        if(questionBoards.size()==0){
            return null;
        }else {
            return questionBoards;
        }
    }

    @Transactional
    public List<QuestionBoard> writeQuestionBoard(QuestionBoard questionBoard) {

        questionBoardRepository.save(questionBoard);

        return loadFromProductBoard(questionBoard.getProductBoardNum());
    }


    @Transactional
    public List<QuestionBoard> writeAnswerBoard(QuestionAnswer questionAnswer) {

        questionAnswerRepository.save(questionAnswer);

        List<QuestionBoard> questionBoards=
        questionBoardRepository
                .findAllByProductBoardNumAndQuestionBoardDeleted(questionAnswer.getQuestionBoardNum(),"false");

        if(questionBoards.size()==0){
            return null;
        }else {
            return questionBoards;
        }

    }

}
