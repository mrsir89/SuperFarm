package com.project.superfarm.controller;

import com.project.superfarm.entity.board.ProductBoard;
import com.project.superfarm.service.ProductBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
             @apiNote      : 제품 등록 게시판
             @Url /product : /all,  추가
             @brief        : /upper 상위 카테고리로 검색
                           : /lower 하위 카테고리로 검색
                           : /search  검색
 **/
@RestController
@RequestMapping("/product")
public class ProductBoardController {

    @Autowired
    private ProductBoardService productBoardService;


    /**
     * @apiNote      : 전체 프로덕트 보드 리턴
     * @Url          : /product/all
     * @See          : java : ProductBoard.java, QuestionBoard.java , QuestionAnswer.java, ReviewBoard.java\n
     *                 DB   : product_board, question_board, question_answer, review_bard
     * @return       :        "productBoardNum": 1,
     *         "upperCode": 1,
     *         "lowerCode": 1,
     *         "productBoardTitle": "정말 좋은 이천쌀 입니다.",
     *         "productBoardThumbnail": "./resource/thumbnail01.jpg",
     *         "productBoardDeliveryPrice": 3000,
     *         "productBoardBest": 0,
     *         "productBoardTag": "쌀,이천쌀,햅쌀",
     *         "productBoardLink": null,
     *         "productBoardCommon": "./resource/common01.jpg",
     *         "productBoardDetail": "./resource/detail01.jpg",
     *         "productBoardBottom": "./resource/bottom01.jpg",
     *         "productEditContent": "여기는 product_edit_content 공간 입니다용",
     *         "productAddDate": "2019-07-31 10:15:12",
     *         "productBoardDeleted": "false",
     *         "product": {
     *             "productCode": 1,
     *             "productName": "이천쌀",
     *             "lowerCode": 2,
     *             "productTypecode": 1,
     *             "productOption1": "20kg",
     *             "productOption2": "햅쌀",
     *             "productPrice": 1000000,
     *             "productMadeDate": "2019-06-01T00:00:00.000+0000",
     *             "productNotaxPrice": 909091,
     *             "productTaxprice": 90909,
     *             "productTax": 0.1,
     *             "productStock": 0,
     *             "productTotalSales": 0,
     *             "productStatus": "true",
     *             "farmerId": "s001",
     *             "productType": {
     *                 "productTypeCode": 1,
     *                 "productTypeName": "이천쌀",
     *                 "productUnit": "Kg",
     *                 "productOrigin": "경기도 이천"
     *             }
     *         },
     *         "questionBoard": {
     *             "questionBoardNum": 1,
     *             "productBoardNum": 5,
     *             "userId": "tester01",
     *             "questionBoardPassword": "1234",
     *             "questionBoardTitle": "테스트 제목 입니다.",
     *             "questionBoardContent": "이거 제품에 대해 너무 비싼거 아니에요?",
     *             "questionBoardRegdate": "2019-07-31T10:22:12.000+0000",
     *             "questionBoardStatus": "false",
     *             "questionBoardDeleted": "false",
     *             "questionBoardDeleteDate": null,
     *             "questionAnswer": [
     *                 {
     *                     "answerNum": 2,
     *                     "answerDate": "2019-07-31T13:23:14.000+0000",
     *                     "answerContent": "거 답글을 제대로 다시오",
     *                     "questionBoardNum": 1,
     *                     "answerWriter": "tester01",
     *                     "answerDeleted": "false",
     *                     "answerBoardDeletedDate": null
     *                 }
     *             ]
     *         },
     *         "reviewBoard": {
     *             "reviewBoardNum": 1,
     *             "productBoardNum": 5,
     *             "customerId": "tester01",
     *             "reviewBoardImg": "./resource/review01.jpg",
     *             "reviewBoardTitle": "제목있음",
     *             "reviewBoardContent": "이 등심 누가 만들었등심 ",
     *             "reviewBoardRating": 5,
     *             "reviewBoardRegDate": "2019-07-22T10:12:11.000+0000",
     *             "reviewBoardDeleted": "false",
     *             "reviewBoardDeleteDate": null
     *         }
     *     },
     *
     */
    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(value = "/all",
                    method = {RequestMethod.POST,RequestMethod.GET},
                    produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
                                 MediaType.APPLICATION_ATOM_XML_VALUE})
    public List<ProductBoard> loadProductBoardAll(){

        return productBoardService.loadProductBoardAll();
    }

    /**
     * @apiNote   : 상위 카테고리에 등록되어있는 모든 productBoard 리턴
     * @Url       : /product/upper
     * @See       : java : ProductBoard.java, QuestionBoard.java , QuestionAnswer.java, ReviewBoard.java\n
     *              DB   : product_board, question_board, question_answer, review_bard
     * @param     : int upper
     * @return    : Json  /all 과 같은 타입
     */
    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(path = "/upper",
            method = {RequestMethod.POST,RequestMethod.GET},
            produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE})
    public List<ProductBoard> loadProductBoardUpper(@RequestParam int upper){

        return productBoardService.loadProductBoardUpper(upper);
    }


    /**
     * @apiNote   : 하위 카테고리에 등록되어있는 모든 productBoard 리턴
     * @Url       : /product/lower
     * @See       : java : ProductBoard.java, QuestionBoard.java , QuestionAnswer.java, ReviewBoard.java\n
     *              DB   : product_board, question_board, question_answer, review_bard
     * @param     : int lower
     * @return    : Json  /all 과 같은 타입
     */
    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(path = "/lower",
            method = {RequestMethod.POST,RequestMethod.GET},
            produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE})
    public List<ProductBoard> loadProductBoardLower(@RequestParam(name="lower") int lower){

        return productBoardService.loadProductBoardLower(lower);
    }

    /**
     * @apiNote   : title / tag / productName 안에 검색 하여 결과값을 리턴
     * @Url       : /product/search
     * @See       : java : ProductBoard.java, QuestionBoard.java , QuestionAnswer.java, ReviewBoard.java\n
     *              DB   : product_board, question_board, question_answer, review_bard
     * @param     : String search
     * @return    : Json  /all 과 같은 타입
     */
    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(value = "/search",
            method = {RequestMethod.POST,RequestMethod.GET},
            produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE})
    public List<ProductBoard> loadProductBoardSearch(String search){

        return productBoardService.loadProductBoardSearch(search);

    }


}
