package com.project.superfarm.controller;

import com.project.superfarm.entity.Board.ProductBoard;
import com.project.superfarm.service.ProductBoardService;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/*
    @Product Board Service

    @loadProductBoardAll 전체 ProductBoard Load
    @loadProductBoardUpper upper카테고리 productBoard Load
    @loadProductBoardLower lower카테고리 productBoard Load
    @loadProductBoardSearch title  porudct 이름 또는 tag에서 검색  productBoardLoad

 */

@RestController
@RequestMapping("/product")
public class ProductBoardController {

    @Autowired
    private ProductBoardService productBoardService;


    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(value = "/all",
                    method = {RequestMethod.POST,RequestMethod.GET},
                    produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
                                 MediaType.APPLICATION_ATOM_XML_VALUE})
    public List<ProductBoard> loadProductBoardAll(){

        return productBoardService.loadProductBoardAll();
    }

    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(path = "/upper",
            method = {RequestMethod.POST,RequestMethod.GET},
            produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE})
    public List<ProductBoard> loadProductBoardUpper(@RequestParam int upper){

        return productBoardService.loadProductBoardUpper(upper);
    }


    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(path = "/lower",
            method = {RequestMethod.POST,RequestMethod.GET},
            produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE})
    public List<ProductBoard> loadProductBoardLower(@RequestParam int lower){

        return loadProductBoardLower(lower);
    }

    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(value = "/search",
            method = {RequestMethod.POST,RequestMethod.GET},
            produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE})
    public List<ProductBoard> loadProductBoardSearch(String search){

        return productBoardService.loadProductBoardSearch(search);

    }


}
