package com.project.superfarm.controller;

import com.project.superfarm.entity.board.ProductBoard;
import com.project.superfarm.model.ProductBoardCreateModel;
import com.project.superfarm.model.ProductListModel;
import com.project.superfarm.service.ProductBoardService;
import com.project.superfarm.util.ExceptionList.UrlNotFountException;
import com.project.superfarm.util.isNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @apiNote ProductBoard 관련
 * @deprecated /productBoard   상품리스트 return
 * /create          productBoard 생성 생성
 * /details         상품 보드의 상세 정보
 */
@RestController
@RequestMapping("/productBoard")
public class ProductBoardController {

    @Autowired
    private ProductBoardService productBoardService;


    /**
     * @return :        "productBoardNum": 1,
     * "upperCode": 1,
     * "lowerCode": 1,
     * "productBoardTitle": "정말 좋은 이천쌀 입니다.",
     * "productBoardThumbnail": "./resource/thumbnail01.jpg",
     * "productBoardDeliveryPrice": 3000,
     * "productBoardBest": 0,
     * "productBoardTag": "쌀,이천쌀,햅쌀",
     * "productBoardLink": null,
     * "productBoardCommon": "./resource/common01.jpg",
     * "productBoardDetail": "./resource/detail01.jpg",
     * "productBoardBottom": "./resource/bottom01.jpg",
     * "productEditContent": "여기는 product_edit_content 공간 입니다용",
     * "productAddDate": "2019-07-31 10:15:12",
     * "productBoardDeleted": "false",
     * "product": {
     * "productCode": 1,
     * "productName": "이천쌀",
     * "lowerCode": 2,
     * "productTypecode": 1,
     * "productOption1": "20kg",
     * "productOption2": "햅쌀",
     * "productPrice": 1000000,
     * "productMadeDate": "2019-06-01T00:00:00.000+0000",
     * "productNotaxPrice": 909091,
     * "productTaxprice": 90909,
     * "productTax": 0.1,
     * "productStock": 0,
     * "productTotalSales": 0,
     * "productStatus": "true",
     * "farmerId": "s001",
     * "productType": {
     * "productTypeCode": 1,
     * "productTypeName": "이천쌀",
     * "productUnit": "Kg",
     * "productOrigin": "경기도 이천"
     * }
     * },
     * "questionBoard": {
     * "questionBoardNum": 1,
     * "productBoardNum": 5,
     * "userId": "tester01",
     * "questionBoardPassword": "1234",
     * "questionBoardTitle": "테스트 제목 입니다.",
     * "questionBoardContent": "이거 제품에 대해 너무 비싼거 아니에요?",
     * "questionBoardRegdate": "2019-07-31T10:22:12.000+0000",
     * "questionBoardStatus": "false",
     * "questionBoardDeleted": "false",
     * "questionBoardDeleteDate": null,
     * "questionAnswer": [
     * {
     * "answerNum": 2,
     * "answerDate": "2019-07-31T13:23:14.000+0000",
     * "answerContent": "거 답글을 제대로 다시오",
     * "questionBoardNum": 1,
     * "answerWriter": "tester01",
     * "answerDeleted": "false",
     * "answerBoardDeletedDate": null
     * }
     * ]
     * },
     * "reviewBoard": {
     * "reviewBoardNum": 1,
     * "productBoardNum": 5,
     * "customerId": "tester01",
     * "reviewBoardImg": "./resource/review01.jpg",
     * "reviewBoardTitle": "제목있음",
     * "reviewBoardContent": "이 등심 누가 만들었등심 ",
     * "reviewBoardRating": 5,
     * "reviewBoardRegDate": "2019-07-22T10:12:11.000+0000",
     * "reviewBoardDeleted": "false",
     * "reviewBoardDeleteDate": null
     * }
     * },
     * @apiNote : 전체 프로덕트 보드 리턴
     * @Url : /product/all
     * @See : java : ProductBoard.java, QuestionBoard.java , QuestionAnswer.java, ReviewBoard.java\n
     * DB   : product_board, question_board, question_answer, review_bard
     */
    @PreAuthorize("hasAnyRole('GUEST','CUSTOMER','ADMIN')")
    @RequestMapping(
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public List<ProductListModel> loadProductBoard(
            @RequestParam(name = "upper", defaultValue = "null", required = false) String upper,
            @RequestParam(name = "lower", defaultValue = "null", required = false) String lower,
            @RequestParam(name = "search", defaultValue = "null", required = false) String search) {
        System.out.println("===============  시작    ========");

        if (upper.equals("null") && lower.equals("null") && search.equals("null")) {
            return productBoardService.findByAllProductBoard();

        } else if (upper != null && lower.equals("null") && search.equals("null")) {

            int intUpper = 0;

            if (isNumber.isStringInteger(upper)) {
                intUpper = Integer.parseInt(upper);
            }
            return productBoardService.findByUpperProductBoard(intUpper);

        } else if (lower != "null" && upper.equals("null") && search.equals("null")) {

            System.out.println("=======Lower =============");
            int intLower = 0;
            if (isNumber.isStringInteger(lower)) {
                intLower = Integer.parseInt(lower);
            }
            return productBoardService.findByLowerProductBoard(intLower);

        } else {

            System.out.println("======= Search 실행 ==========");
            return productBoardService.findBySearchProductBoard(search);
        }
    }

//

    /**
     * @param num
     * @return
     * @throws ClassNotFoundException
     * @apiNote productBoard의 상세 정보 단1개만 들어 있는 정보를 return
     */
    @PreAuthorize("hasAnyRole('GUEST','CUSTOMER','ADMIN')")
    @RequestMapping(value = "/detail",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public ProductBoard loadProductDetail(@RequestParam(name = "num") String num) {
        System.out.println("===== 시작 ==============");
        Long longNum = 0L;
        if (num != null) {
            if (isNumber.isStringLong(num))
                longNum = Long.parseLong(num);
            return productBoardService.loadProductDetails(longNum);

        } else
            throw new UrlNotFountException();

    }

    /**
     * @param
     * @return productBoard
     * @apiNote 메인 화면 베스트 상품 리턴
     */
    @PreAuthorize("hasAnyRole('GUEST','CUSTOMER','ADMIN')")
    @RequestMapping(value = "/best",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public List<ProductListModel> loadMainProduct() {

            return productBoardService.loadMainProduct();
    }

    /**
     * todo:삭제 후 위의 bestMain과 합쳐질 예정
     *
     * @param lower, main
     * @return
     * @apiNote 카테고리 화면의 best 상품 product_best의 항목이 0보다 큰것을 return
     */
    @PreAuthorize("hasAnyRole('GUEST','CUSTOMER','ADMIN')")
    @RequestMapping(value = "/bestLower",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public List<ProductListModel>  loadLowerBestProduct(@RequestParam(name = "lower") Integer lower) {

        System.out.println(" BestProduct List 실행 ");
        return productBoardService.loadLowerBestProduct(lower);

    }

    /**
     * Test용 API
     * @param lower
     * @return
     */
    @PreAuthorize("hasAnyRole('GUEST','CUSTOMER','ADMIN')")
    @RequestMapping(value = "/test",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public List<ProductListModel> loadProductBoard(@RequestParam(name = "lower") Integer lower) {

        return productBoardService.findByLowerProductBoard(lower);

    }

    /**
     * @param productBoardCreateModel
     * @return productBoard
     * @apiNote ProductBoard 생성  model안에 넘어 오는 status 상태가 short이면 임시 저장
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/create",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE
            })
    public ProductBoard createProductBoard(@RequestBody ProductBoardCreateModel productBoardCreateModel) {

        if (productBoardCreateModel != null) {
            if (productBoardCreateModel.getStatus().equals("short")) {
                return productBoardService.createProduct(productBoardCreateModel);

            } else {
                throw new UrlNotFountException();
            }

        } else {
            throw new UrlNotFountException();
        }
    }

}


//
//
//    /**
//     * @apiNote   : title / tag / productName 안에 검색 하여 결과값을 리턴
//     * @Url       : /product/search
//     * @See       : java : ProductBoard.java, QuestionBoard.java , QuestionAnswer.java, ReviewBoard.java\n
//     *              DB   : product_board, question_board, question_answer, review_bard
//     * @param     : String search
//     * @return    : Json  /all 과 같은 타입
//     */
//    @PreAuthorize("hasAnyRole('Guest','customer')")
//    @RequestMapping(value = "/search",
//            method = {RequestMethod.POST,RequestMethod.GET},
//            produces = {
//                MediaType.APPLICATION_JSON_UTF8_VALUE,
//                MediaType.APPLICATION_ATOM_XML_VALUE
//    })
//    public List<ProductBoard> loadProductBoardSearch(String search){
//
//        if(search != null) {
//            return productBoardService.loadProductBoardSearch(search);
//
//        }else
//            throw new UrlNotFountException();
//
//    }


/**
 * //     * @apiNote   : 상위 카테고리에 등록되어있는 모든 productBoard 리턴
 * //     * @Url       : /product/upper
 * //     * @See       : java : ProductBoard.java, QuestionBoard.java , QuestionAnswer.java, ReviewBoard.java\n
 * //     *              DB   : product_board, question_board, question_answer, review_bard
 * //     * @param     : int upper
 * //     * @return    : Json  /all 과 같은 타입
 * //
 */
//    @PreAuthorize("hasAnyRole('Guest','customer')")
//    @RequestMapping(path = "/upper",
//            method = RequestMethod.POST,
//            produces = {
//                    MediaType.APPLICATION_JSON_UTF8_VALUE,
//                    MediaType.APPLICATION_ATOM_XML_VALUE
//            })
//    public List<ProductBoard> loadProductBoardUpper(@RequestParam Integer upper) {
//
//        if(upper != null || upper > 0) {
//            return productBoardService.loadProductBoardUpper(upper);
//
//        }else
//            throw new UrlNotFountException();
//    }
//
//
//    /**
//     * @apiNote   : 하위 카테고리에 등록되어있는 모든 productBoard 리턴
//     * @Url       : /product/lower
//     * @See       : java : ProductBoard.java, QuestionBoard.java , QuestionAnswer.java, ReviewBoard.java\n
//     *              DB   : product_board, question_board, question_answer, review_bard
//     * @param     : int lower
//     * @return    : Json  /all 과 같은 타입
//     */
//    @PreAuthorize("hasAnyRole('Guest','customer')")
//    @RequestMapping(path = "/lower",
//            method = {RequestMethod.POST,RequestMethod.GET},
//            produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
//                    MediaType.APPLICATION_ATOM_XML_VALUE})
//    public List<ProductBoard> loadProductBoardLower(@RequestParam(name="lower") Integer lower){
//
//        if(lower != null || lower > 0) {
//            return productBoardService.loadProductBoardLower(lower);
//
//        }else
//            return null;
//    }