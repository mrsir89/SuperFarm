package com.project.superfarm.service;

import com.project.superfarm.entity.board.QuestionAnswer;
import com.project.superfarm.entity.board.QuestionBoard;
import com.project.superfarm.repository.boardRepository.QuestionAnswerRepository;
import com.project.superfarm.repository.boardRepository.QuestionBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class QuestionBoardService {



    @Autowired
    private QuestionBoardRepository questionBoardRepository;

    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;


    public Page<QuestionBoard> loadFromProductBoard(Long productBoardNum, Pageable pageable){

        Page<QuestionBoard> questionBoards =
                questionBoardRepository
                        .findAllByProductBoardNumAndQuestionBoardDeleted(productBoardNum,"false",pageable);
        if(questionBoards.getTotalPages()==0){
            return null;
        }else{
            return questionBoards;
        }
    }
    public Page<QuestionBoard> testttt(Pageable pageable){

       Page<QuestionBoard> qqq = questionBoardRepository
               .findAllByProductBoardNumAndQuestionBoardDeletedAndQuestionAnswer_AnswerDeleted(
                       5L,"false","false",pageable);
       return qqq;
    }


    // UserId으로 검색 하면서 deleted가 false인 것들만
    public Page<QuestionBoard> loadFromUserId(String userId,Pageable pageable) {

        Page<QuestionBoard> questionBoards =
                questionBoardRepository.findAllByUserIdAndQuestionBoardDeleted(userId,"false",pageable);

        if(questionBoards.isEmpty()){
            System.out.println("null 이거 is empty 실행 확인");
            return null;

        }else {
            return questionBoards;
        }
    }

    private QuestionBoard loadQuestionBoard(Long questionBoardNum){

        Optional<QuestionBoard> questionBoard =
                questionBoardRepository.findById(questionBoardNum);
        if(questionBoard.isPresent()){
            return questionBoard.get();
        }else{
            return new QuestionBoard();
        }
    }

    //Q/a 작성
    @Transactional
    public QuestionBoard writeQuestionBoard(QuestionBoard questionBoard) {

          return questionBoardRepository.save(questionBoard);
    }

    // 댓글 작성
    @Transactional
    public QuestionAnswer writeAnswerBoard(QuestionAnswer questionAnswer) {

        return questionAnswerRepository.save(questionAnswer);

    }

    @Transactional
    public QuestionBoard updateAnswer(QuestionAnswer questionAnswer){

        int result =
                questionAnswerRepository.updateAnswer(questionAnswer.getAnswerContent(),questionAnswer.getAnswerNum());

        if(result ==1){
            return loadQuestionBoard(questionAnswer.getQuestionBoardNum());
        }else{
            return null;
        }
    }
    @Transactional
    public QuestionBoard deletedAnswer(QuestionAnswer questionAnswer){
        int result =
                questionAnswerRepository.deleteAnswer(questionAnswer.getAnswerNum());
        if(result==1){
            return loadQuestionBoard(questionAnswer.getQuestionBoardNum());
        }else
            return null;
    }

    @Transactional
    public QuestionBoard updateQuestionBoard(QuestionBoard questionBoard) {

        return questionBoardRepository.save(questionBoard);

    }

    @Transactional
    public QuestionBoard deletedQuestionBoard(QuestionBoard questionBoard){
        int result =
                questionBoardRepository.deleteQuestionBoard(questionBoard.getQuestionBoardNum());
        if(result==1){
            return loadQuestionBoard(questionBoard.getQuestionBoardNum());
        }else
            return null;
    }

}
