package com.project.superfarm.service;

import com.project.superfarm.entity.board.QuestionAnswer;
import com.project.superfarm.entity.board.QuestionBoard;
import com.project.superfarm.repository.boardRepository.QuestionAnswerRepository;
import com.project.superfarm.repository.boardRepository.QuestionBoardRepository;
import com.project.superfarm.util.ExceptionList.UrlNoLoginAndNotMyself;
import com.project.superfarm.util.ExceptionList.UrlNotFountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class QuestionBoardService {


    @Autowired
    private QuestionBoardRepository questionBoardRepository;

    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;


    public Page<QuestionBoard> loadFromProductBoard(Long productBoardNum, Pageable pageable) {

        Page<QuestionBoard> questionBoards =
                questionBoardRepository
                        .findAllByProductBoardNumAndQuestionBoardDeleted(productBoardNum, "false", pageable);
        return questionBoards;
    }


    // UserId으로 검색 하면서 deleted가 false인 것들만
    public Page<QuestionBoard> loadFromUserId(String userId, Pageable pageable) {

        Page<QuestionBoard> questionBoards =
                questionBoardRepository.findAllByUserIdAndQuestionBoardDeleted(userId, "false", pageable);

        if (questionBoards.isEmpty()) {
            System.out.println("null 이거 is empty 실행 확인");
            return null;

        } else {
            return questionBoards;
        }
    }

    private QuestionBoard loadQuestionBoard(Long questionBoardNum) {

        Optional<QuestionBoard> questionBoard =
                questionBoardRepository.findById(questionBoardNum);
        if (questionBoard.isPresent()) {
            return questionBoard.get();
        } else {
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

        QuestionAnswer returnQuestionAnswer = questionAnswerRepository.save(questionAnswer);
        if (returnQuestionAnswer != null) {

            Long questionBoardNum = returnQuestionAnswer.getQuestionBoardNum();
            int result = questionBoardRepository.updateQuestionBoardStatus(questionBoardNum);
            return returnQuestionAnswer;

        } else {
            throw new UrlNotFountException();
        }


    }

    @Transactional
    public QuestionBoard updateAnswer(QuestionAnswer questionAnswer) {

        int result =
                questionAnswerRepository.updateAnswer(questionAnswer.getAnswerContent(), questionAnswer.getAnswerNum());

        if (result == 1) {
            return loadQuestionBoard(questionAnswer.getQuestionBoardNum());
        } else {
            return null;
        }
    }

    /**
     * @param questionAnswer
     * @return QuestionBoard
     * @throws ClassNotFoundException
     * @apiNote QnA 게시판 댓글을 softDelete
     */
    @Transactional
    public QuestionBoard deletedAnswer(QuestionAnswer questionAnswer) {

        String requestId = questionAnswer.getAnswerWriter();
        Long requestBoardNum = questionAnswer.getAnswerNum();
        System.out.println(questionAnswer.toString());
        Optional<QuestionAnswer> check = questionAnswerRepository.findById(requestBoardNum);

        String originUserId = check.get().getAnswerWriter();

        if (requestId.equals(originUserId)) {
            int result =
                    questionAnswerRepository.deleteAnswer(requestBoardNum);
            if (result == 1) {
                return loadQuestionBoard(requestBoardNum);
            } else
                throw new UrlNoLoginAndNotMyself();
        } else {
            throw new UrlNoLoginAndNotMyself();
        }


    }

    @Transactional
    public QuestionBoard updateQuestionBoard(QuestionBoard questionBoard) {

        return questionBoardRepository.save(questionBoard);

    }


    @Transactional
    public QuestionBoard deletedQuestionBoard(QuestionBoard questionBoard) {
        int result =
                questionBoardRepository.deleteQuestionBoard(questionBoard.getQuestionBoardNum());
        if (result == 1) {
            return loadQuestionBoard(questionBoard.getQuestionBoardNum());
        } else
            return null;
    }

}
