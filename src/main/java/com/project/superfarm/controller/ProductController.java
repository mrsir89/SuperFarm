package com.project.superfarm.controller;


import com.project.superfarm.entity.product.Product;
import com.project.superfarm.service.ProductItemService;
import com.project.superfarm.util.ExceptionList.UrlNotFountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
         @apiNote       : 제품 조회
         @Url /product  : /all,  전체 조회
         @brief         : /upper 상위 카테고리로 검색
                        : /lower 하위 카테고리로 검색
                        : /name  제품명으로 검색
 **/
@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductItemService productItemService;



    /**
     * @apiNote     :  제품의 전체 등록 제품을 가져 온다
     * @Url         : /product/item/all
     * @see         : java : product.java, ProductType.java
     *                DB   : product, product_type
     * @return      :JSON
     *           {
     *                "productCode": 1,
     *                "productName": "이천쌀",
     *                "lowerCode": 2,
     *                "productTypecode": 1,
     *                "productOption1": "20kg",
     *                "productOption2": "햅쌀",
     *                "productPrice": 1000000,
     *                "productMadeDate": "2019-06-01T00:00:00.000+0000",
     *                "productNotaxPrice": 909091,
     *                "productTaxprice": 90909,
     *                "productTax": 0.1,
     *                "productStock": 0,
     *                "productTotalSales": 0,
     *                "productStatus": "true",
     *                "farmerId": "s001",
     *                "productType": {
     *                       "productTypeCode": 1,
     *                       "productTypeName": "이천쌀",
     *                       "productUnit": "Kg",
     *                       "productOrigin": "경기도 이천"
     *                                }
     *           }
     */

    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(path="/all",
            method = RequestMethod.GET,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE })
    public List<Product> loadItemAll(){

        return productItemService.loadItemAll();

    }


    /**
     * @apiNote     :  제품의 lower(하위카테고리)에 등록 제품을 가져 온다
     * @Url         : /product/item/lower
     * @param       : int lower
     * @see         : java : product.java, ProductType.java
     *                DB   : product, product_type
     * @return      : JSON
     *              : /all return과 같다
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path="/lower",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public List<Product> loadItemFromLowerCategory(@RequestParam(name="lower") Integer lower){

        if(lower != null ) {
            return productItemService.fromLowerCategory(lower);

        }else
            throw new UrlNotFountException();
    }


    /**
     * @apiNote     :  제품의 upper(상위 카테고리)에 등록 제품을 가져 온다
     * @Url         : /product/item/upper
     * @param       : int upper
     * @see         : java : product.java, ProductType.java
     *                DB   : product, product_type
     * @return      :JSON
     *              : /all return 과 같다.
    */

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path="/upper",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public List<Product> loadItemFromUpperCategory(@RequestParam(name="upper") Integer upper){

        if(upper != null) {
            return productItemService.fromLowerCategory(upper);

        }else
            throw new UrlNotFountException();

    }


    /**
     * @apiNote     :  입력한 이름의 등록 제품을 가져 온다
     * @Url         : /product/item/name
     * @param       : String name
     * @see         : java : product.java, ProductType.java
     *                DB   : product, product_type
     * @return      :JSON
     *              : /all return 과 같다.
     */

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path="/name",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public List<Product> loadFindItemName(@RequestParam(name="name") String name){

        if(name != null) {
            return productItemService.loadFindItemName(name);

        }else
            throw new UrlNotFountException();
    }




}
