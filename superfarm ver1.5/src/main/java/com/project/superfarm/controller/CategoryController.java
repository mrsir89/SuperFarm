package com.project.superfarm.controller;


import com.project.superfarm.entity.LowerCategory;
import com.project.superfarm.entity.UpperCategory;
import com.project.superfarm.service.CategoryServiece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/*
    @Category API

    @loadAll    전체 카테고리 제공
    @loadUpperCategory 카 테고리 상위 카테고리 검색으로 제공

 */
@RequestMapping("/category")
@RestController
public class CategoryController {


    @Autowired
    private CategoryServiece categoryServiece;

    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(path="/all",
                    method = {RequestMethod.POST,RequestMethod.GET},
                    produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
                                MediaType.APPLICATION_ATOM_XML_VALUE})
    public List<UpperCategory> loadAll(){

        return  categoryServiece.findall();

    }

//    @PreAuthorize("hasRole('GUEST')")
//    @RequestMapping(path="/upper",
//                    method = {RequestMethod.POST,RequestMethod.GET},
//                    produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
//                                MediaType.APPLICATION_ATOM_XML_VALUE})
//    public List<UpperCategory> loadUpperCategory(@RequestParam Integer upper_code){
//
//        return categoryServiece.findUpperCategory(upper_code);
//
//    }
//
//    @PreAuthorize("hasRole('GUEST')")
//    @RequestMapping(path="lower",
//                    method={RequestMethod.POST,RequestMethod.GET},
//                    produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
//                                MediaType.APPLICATION_ATOM_XML_VALUE})
//    public List<LowerCategory> loadLowerCategory(@RequestParam Integer upperCode
//                                    ,@RequestParam Integer lowerCode){
//
//        return categoryServiece.findLowerCategory(upperCode,lowerCode);
//    }

}
