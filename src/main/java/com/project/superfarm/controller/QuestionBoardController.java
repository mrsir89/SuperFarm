package com.project.superfarm.controller;


import com.project.superfarm.entity.board.QuestionAnswer;
import com.project.superfarm.entity.board.QuestionBoard;
import com.project.superfarm.model.ResultItems;
import com.project.superfarm.service.QuestionBoardService;
import com.project.superfarm.util.ExceptionList.UrlNotFountException;
import com.project.superfarm.util.isNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * TODO : ID 방식으로 검색 해 오기
 * TODO : 내용 검색으로 검색할수 있게 해 오기  ProductBoard에서
 *
 * @apiNote : QnA 게시판 Page 형식으로 되어있다.
 * @Url /question : /product,  productBoardNum으로 QnA 게시판 리턴
 * @brief : /userId   Id로 검색
 * : /write/question QnA 게시판 글쓰기
 * : /write/answer   QnA 게시판내 댓글 글쓰기
 **/


@RequestMapping("/question")
@RestController
public class QuestionBoardController {

    @Autowired
    private QuestionBoardService questionBoardService;


    // TODO 질문 사항  Stream으로 컬렉션을 넣을때는 정렬이 안되고  getContent List로 넣을때는 정렬이 된다.


    /**
     * : 못받는 error 수정중 2019.09.02 완료
     *
     * @param : long productNum,
     *          int page,
     *          int size,
     *          String sort,
     * @return : Json
     * "items": [
     * {
     * "questionBoardNum": 12,
     * "productBoardNum": 5,
     * "userId": "tester01",
     * "questionBoardPassword": "1234",
     * "questionBoardTitle": "zzzzzzzzzzzzz3",
     * "questionBoardContent": "어????????????????????? ",
     * "questionBoardRegdate": "2019-07-31T15:23:12.000+0000",
     * "questionBoardStatus": "false",
     * "questionBoardDeleted": "false",
     * "questionBoardDeleteDate": null,
     * "questionAnswer": [
     * {
     * "answerNum": 18,
     * "answerDate": "2019-07-31T23:32:14.000+0000",
     * "answerContent": "냉 무",
     * "questionBoardNum": 12,
     * "answerWriter": "관리자",
     * "answerDeleted": "false",
     * "answerBoardDeletedDate": null
     * }
     * ]
     * },
     * <p>
     * "page": 2,
     * "size": 10,
     * "totalCount": 16,
     * "totalPage": 2,
     * "hasNext": false,
     * "boardNum": 5,
     * @apiNote : 상위 카테고리에 등록되어있는 모든 productBoard 리턴
     * @Url : /product
     * @See : java : QuestionBoard.java, QuestionAnswer.java \n
     * DB   : question_board, question_answer
     */

    @PreAuthorize("hasAnyRole('GUEST','CUSTOMER','ADMIN')")
    @RequestMapping(path = "/product",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public ResultItems<QuestionBoard> loadFromProductBoard(
            @RequestParam(name = "productBoardNum", required = false) String productBoardNum,
            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
            @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(name = "sort", defaultValue = "questionBoardNum", required = false) String sort) {

        System.out.println("--------productNum" + productBoardNum);
        System.out.println("----------- page" + page + "-------------- size " + size);

        if (productBoardNum != null) {
            if (isNumber.isStringLong(productBoardNum)) {

                Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort).descending());
                Page<QuestionBoard> questionBoards =
                        questionBoardService.loadFromProductBoard(Long.parseLong(productBoardNum), pageable);

                if (questionBoards == null) {

                    throw new UrlNotFountException();

                } else {
                    ResultItems<QuestionBoard> questionBoardResultItems = new ResultItems<>(questionBoards.getContent(), page, size,
                            questionBoards.getTotalElements(), questionBoards.getTotalPages(), questionBoards.hasNext());
                    questionBoardResultItems.setBoardNum(Long.parseLong(productBoardNum));
                    return questionBoardResultItems;
                }
            } else {
                throw new UrlNotFountException();
            }
        } else
            throw new UrlNotFountException();
    }


    /**
     * @param : String userId,
     *          int page,
     *          int size,
     *          String sort,
     * @return : Json
     * : /product 와 같음
     * @apiNote : userId로 등록되어있는 모든 productBoard 리턴
     * @Url : /userId
     * @See : java : QuestionBoard.java, QuestionAnswer.java \n
     * DB   : question_board, question_answer
     */
    @PreAuthorize("hasAnyRole('GUEST','CUSTOMER','ADMIN')")
    @RequestMapping(path = "/userId",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public ResultItems<QuestionBoard> loadFromCustomerId(
            @RequestParam(name = "userId", required = false) String userId,
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @RequestParam(name = "sort", defaultValue = "questionBoardNum", required = false) String sort) {
        // 1 . UserId 체크
        if (userId != null) {
            Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort).descending());
            Page<QuestionBoard> questionBoards = questionBoardService.loadFromUserId(userId, pageable);

            if (questionBoards == null) {
                throw new UrlNotFountException();

            } else {
                ResultItems<QuestionBoard> questionBoardResultItems = new ResultItems<>(questionBoards.getContent(), page, size,
                        questionBoards.getTotalElements(), questionBoards.getTotalPages(), questionBoards.hasNext());
                return questionBoardResultItems;
            }

        } else
            throw new UrlNotFountException();

    }

    /**
     * @param : Json QuestionBoar.java
     *          "productBoardNum": 5,
     *          "userId": "tester01",
     *          "questionBoardPassword": "1234",
     *          "questionBoardTitle": "zzzzzzzzzzzzz3",
     *          "questionBoardContent": "어????????????????????? ",
     * @return : Json
     * : /product 와 같음
     * @apiNote : productBoard에 속한 QnA 게시판에 글쓰기
     * @Url : /write/question
     * @See : java : QuestionBoard.java \n
     * DB   : question_board
     */
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @RequestMapping(path = "/write/question",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public QuestionBoard writeQuestionBoard(@RequestBody QuestionBoard questionBoard) {

        if (questionBoard != null) {
            return questionBoardService.writeQuestionBoard(questionBoard);

        } else
            throw new UrlNotFountException();

    }

    /**
     * @param : Json QuestionAnswer.java
     *          "answerContent": "냉 무",
     *          "questionBoardNum": 12,
     *          "answerWriter": "관리자",
     * @return : Json
     * : /product 와 같음
     * @apiNote : ProductBoard에 속한 QnA 게시판에 댓글 쓰기
     * @Url : /write/answer
     * @See : java : QuestionBoard.java, QuestionAnswer\n
     * DB   : question_board, question_answer
     */
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @RequestMapping(path = "/write/answer",
            method = {RequestMethod.GET, RequestMethod.POST},
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public QuestionAnswer writeAnswerBoard(@RequestBody QuestionAnswer questionAnswer) {

        if (questionAnswer != null) {
            return questionBoardService.writeAnswerBoard(questionAnswer);

        } else
            throw new UrlNotFountException();
    }


    /**
     * @param : Json QuestionBoar.java
     *          "productBoardNum": 5,
     *          "userId": "tester01",
     *          "questionBoardPassword": "1234",
     *          "questionBoardTitle": "zzzzzzzzzzzzz3",
     *          "questionBoardContent": "어????????????????????? ",
     *          "questionAnswer": []
     * @return : Json
     * : /product 와 같음
     * @apiNote : productBoard에 속한 QnA보드의 내용 수정
     * @Url : /update/question
     * @See : java : QuestionBoard.java \n
     * DB   : question_board
     */
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @RequestMapping(path = "/update/question",
            method = {RequestMethod.PATCH, RequestMethod.POST},
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public QuestionBoard updateQuestionBoard(@RequestBody QuestionBoard questionBoard) {

        if (questionBoard != null) {
            return questionBoardService.updateQuestionBoard(questionBoard);

        } else
            throw new UrlNotFountException();
    }

    /**
     * @param : Json QuestionAnswer.java
     *          "answerNum":"12",
     *          "answerContent": "냉 무",
     *          "questionBoardNum": 12,
     *          "answerWriter": "관리자",
     * @return : Json
     * : /product 와 같음
     * @apiNote : userId로 등록되어있는 questionBoard answerNum 수정후 리턴
     * @Url : /update/answer
     * @See : java : QuestionBoard.java, QuestionAnswer\n
     * DB   : question_board, question_answer
     */
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @RequestMapping(path = "/update/answer",
            method = RequestMethod.PATCH,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE})
    public QuestionBoard updateAnswerBoard(@RequestBody QuestionAnswer questionAnswer) {

        System.out.println(questionAnswer.toString());

        if (questionAnswer != null) {
            return questionBoardService.updateAnswer(questionAnswer);

        } else
            throw new UrlNotFountException();
    }

    /**
     * @param : Json QuestionAnswer.java
     *          "answerNum":"12",
     *          "answerContent": "냉 무",
     *          "questionBoardNum": 12,
     *          "answerWriter": "관리자",
     * @return : Json
     * : /product 와 같음
     * @apiNote :  questionBoard answerNum softDelete 실제 삭제가 아닌 감추기 후 현재 questionBoard 리턴
     * @Url : /delete/answer
     * @See : java : QuestionBoard.java, QuestionAnswer\n
     * DB   : question_board, question_answer
     */
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @RequestMapping(path = "/delete/answer",
            method = RequestMethod.DELETE,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public QuestionBoard deleteAnswer(@RequestBody QuestionAnswer questionAnswer) {

        System.out.println(questionAnswer.toString());

        if (questionAnswer != null) {
            return questionBoardService.deletedAnswer(questionAnswer);

        } else
            throw new UrlNotFountException();
    }

    /**
     * @param : Json QuestionBoar.java
     *          "productBoardNum": 5,
     *          "userId": "tester01",
     *          "questionBoardPassword": "1234",
     *          "questionBoardTitle": "zzzzzzzzzzzzz3",
     *          "questionBoardContent": "어????????????????????? ",
     *          "questionAnswer": []
     * @return : Json
     * : /product 와 같음
     * @apiNote : questionBoardNum 으로 soft 삭제(실제 삭제가 아닌 감추기) 후 빈칸리턴
     * @Url : /delete/question
     * @See : java : QuestionBoard.java \n
     * DB   : question_board
     */
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @RequestMapping(path = "/delete/question",
            method = RequestMethod.DELETE,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public QuestionBoard deleteQuestionBoard(@RequestBody QuestionBoard questionBoard) {

        System.out.println(questionBoard.toString());

        if (questionBoard != null) {
            return questionBoardService.deletedQuestionBoard(questionBoard);

        } else
            throw new UrlNotFountException();
    }


}
