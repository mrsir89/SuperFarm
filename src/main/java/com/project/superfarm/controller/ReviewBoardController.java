package com.project.superfarm.controller;


import com.project.superfarm.entity.board.ReviewBoard;
import com.project.superfarm.model.ResultItems;
import com.project.superfarm.model.ReturnModel;
import com.project.superfarm.service.ReviewBoardService;
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
 * @apiNote : Review 게시판 Page 형식으로 되어있다.
 * @Url /review   : /all,  전체 리뷰 게시판 리턴
 * @brief : /write   리뷰 게시판 작성
 * : /delete  리뷰게시판 soft삭제
 * : /update  리뷰게시판 수정
 **/


@RestController
@RequestMapping("/review")
public class ReviewBoardController {


    @Autowired
    private ReviewBoardService reviewBoardService;


    /**
     * @param : int page,
     *          int size,
     *          String sort,
     * @return : Json ResultItem.java
     * :
     * "items": [
     * {
     * "reviewBoardNum": 14,
     * "productBoardNum": 4,
     * "customerId": "tester01",
     * "reviewBoardImg": "./resource/review14.jpg",
     * "reviewBoardTitle": "3000만큼",
     * "reviewBoardContent": "이천쌀이 정말 맛있습니다용 ",
     * "reviewBoardRating": 5,
     * "reviewBoardRegDate": "2019-07-24T17:27:11.000+0000",
     * "reviewBoardDeleted": "false",
     * "reviewBoardDeleteDate": null
     * }
     * @apiNote : 등록되어있는 모든 ReviewBoard 리턴
     * @Url : /review/all
     * @See : java : ReviewBoard.java, \n
     * DB   : review_board,
     */
    @PreAuthorize("hasAnyRole('GUEST','CUSTOMER','ADMIN')")
    @RequestMapping(path = "/all",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public ResultItems<ReviewBoard> loadAllReviewBoard(
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @RequestParam(name = "sort", defaultValue = "reviewBoardNum", required = false) String sort) {

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort).descending());

        Page<ReviewBoard> reviewBoards =
                reviewBoardService.loadAllReviewBoard(pageable);

        return new ResultItems<ReviewBoard>(reviewBoards.getContent(),
                page, size, reviewBoards.getTotalElements(),
                reviewBoards.getTotalPages(), reviewBoards.hasNext());
    }

    /**
     * @param : Long productBoardNum
     *          int page,
     *          int size,
     *          String sort,
     * @return : Json ResultItem.java
     * : /all 과 같음
     * @apiNote : productBoardNum에 속한 ReviewBoard 리턴
     * @Url : /review/all
     * @See : java : ReviewBoard.java, \n
     * DB   : review_board,
     */
    @PreAuthorize("hasAnyRole('GUEST','CUSTOMER','ADMIN')")
    @RequestMapping(path = "/product",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public ResultItems<ReviewBoard> loadFromProductBoard(
            @RequestParam(name = "productBoardNum", required = false) String productBoardNum,
            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
            @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(name = "sort", defaultValue = "reviewBoardNum", required = false) String sort) {

        if (productBoardNum != null) {
            if (isNumber.isStringLong(productBoardNum)) {
                Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort).descending());
                Page<ReviewBoard> reviewBoard =
                        reviewBoardService.loadFromProductBoard(Long.parseLong(productBoardNum), pageable);

                if (reviewBoard == null) {
                    throw new UrlNotFountException();

                } else {
                    ResultItems<ReviewBoard> reviewBoardResultItems
                            = new ResultItems<ReviewBoard>(reviewBoard.getContent(), page, size, reviewBoard.getTotalElements(),
                                                           reviewBoard.getTotalPages(), reviewBoard.hasNext());
                    reviewBoardResultItems.setBoardNum(Long.parseLong(productBoardNum));
                    return reviewBoardResultItems;
                }
            } else
                throw new UrlNotFountException();
        } else
            throw new UrlNotFountException();
    }

    /**
     * @param : Json ReviewBoard.java
     *          "productBoardNum": 4,
     *          "customerId": "tester01",
     *          "reviewBoardImg": "./resource/review14.jpg",
     *          "reviewBoardTitle": "3000만큼",
     *          "reviewBoardContent": "이천쌀이 정말 맛있습니다용 ",
     *          "reviewBoardRating": 5,
     * @return : Json ResultItem.java
     * : /all 과 같음
     * @apiNote : productBoard의  ReviewBoard 작성
     * @Url : /review/write
     * @See : java : ReviewBoard.java, \n
     * DB   : review_board,
     */
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @RequestMapping(path = "/write",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public ReviewBoard writeReviewBoard(@RequestBody ReviewBoard reviewBoard) {

        if (reviewBoard != null) {
            return reviewBoardService.writeReviewBoard(reviewBoard);

        } else
            throw new UrlNotFountException();

    }

    /**
     * Todo : 오류 에대한 예외처리 예정 String -> status 오류 발생 !완료!
     *
     * @param : Json ReviewBoard.java
     *          "reviewBoardNum": "2",
     * @return : "true" / false
     * @apiNote : reviewBoard soft 삭제
     * @Url : /review/delete
     * @See : java : ReviewBoard.java, \n
     * DB   : review_board,
     */
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @RequestMapping(path = "/delete",
            method = RequestMethod.DELETE,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public ReturnModel deleteReviewBoard(@RequestParam Long reviewBoardNum) {

        if (reviewBoardNum != null) {

            return new ReturnModel(reviewBoardService.deleteReviewBoard(reviewBoardNum));

        } else
            throw new UrlNotFountException();

    }

    /**
     * @param : Json ReviewBoard.java
     *          "reviewBoardNum" : 11,
     *          "productBoardNum": 4,
     *          "customerId": "tester01",
     *          "reviewBoardImg": "./resource/review14.jpg",
     *          "reviewBoardTitle": "3001만큼",
     *          "reviewBoardContent": "이천쌀이 정말 맛있습니다용 ",
     * @return : Json ResultItem.java
     * : /all 과 같음
     * @apiNote : productBoard의  ReviewBoard 수정
     * @Url : /review/update
     * @See : java : ReviewBoard.java, \n
     * DB   : review_board,
     */
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @RequestMapping(path = "/update",
            method = RequestMethod.PATCH,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public ReviewBoard updateReviewBoard(@RequestBody ReviewBoard reviewBoard) {

        if (reviewBoard != null) {
            return reviewBoardService.writeReviewBoard(reviewBoard);

        } else
            throw new UrlNotFountException();
    }

}
