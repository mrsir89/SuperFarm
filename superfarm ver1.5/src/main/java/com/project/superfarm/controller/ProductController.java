package com.project.superfarm.controller;


import com.project.superfarm.entity.Product.Product;
import com.project.superfarm.service.ProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
  product 정보 불러 오기

  @loadItemFromLowerCategory 하위 카테고리 id로 아이템 불러 오기
  @loadItemFromUpperCategory 상위 카테고리 id로 아이템 불러 오기
  @loadItemAll 전체 propduct item 전체
  @loadFindItemName 상품 이름으로 불러 오기
 */

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductItemService productItemService;


    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(path="/item/lower",
            method = {RequestMethod.GET,RequestMethod.POST},
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE })
    public List<Product> loadItemFromLowerCategory(@RequestParam Long lower_category_code){

        return productItemService.fromLowerCategory(lower_category_code);

    }
    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(path="/item/upper",
            method = {RequestMethod.GET,RequestMethod.POST},
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE })
    public List<Product> loadItemFromUpperCategory(@RequestParam Long lower_category_code){

        return productItemService.fromLowerCategory(lower_category_code);

    }

    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(path="/item/all",
            method = {RequestMethod.GET,RequestMethod.POST},
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE })
    public List<Product> loadItemAll(){

        return productItemService.loadItemAll();

    }

    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(path="/item/name",
            method = {RequestMethod.GET,RequestMethod.POST},
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE })
    public List<Product> loadFindItemName(@RequestParam String itemName){

        return productItemService.loadFindItemName(itemName);

    }


}
