package com.project.superfarm.controller;


import com.project.superfarm.entity.Board.QuestionAnswer;
import com.project.superfarm.entity.Board.QuestionBoard;
import com.project.superfarm.service.QuestionBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
    TODO : ID 방식으로 검색 해 오기
    TODO : 내용 검색으로 검색할수 있게 해 오기  ProductBoard에서

    @see QuestinoBoad


    @loadFromProductBoard   URL = /question/product
         productBoard Number로 questionboard 및 포함된 answerboard 제공

    @loadFromCustomerId customerId 로 question Board 제공

    @WriteQuestionBoard Q&A작성

    @WriteAnswerBoard   댓글작성

 */


@RequestMapping("/question")
@RestController
public class QuestionBoardController {

    @Autowired
    private QuestionBoardService questionBoardService;

    //완료
    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(path="/product",
            method = {RequestMethod.GET,RequestMethod.POST},
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE })
    public List<QuestionBoard> loadFromProductBoard(@RequestParam Long productNum){
        return questionBoardService.loadFromProductBoard(productNum);

    }
    // 완료
    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(path="/userId",
            method = {RequestMethod.GET,RequestMethod.POST},
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE })
    public List<QuestionBoard> loadFromCustomerId(String userId){
        return questionBoardService.loadFromUserId(userId);
    }
    //완료
    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(path="/write",
            method = {RequestMethod.POST,RequestMethod.GET},
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE })
    public List<QuestionBoard> writeQuestionBoard(@RequestBody QuestionBoard questionBoard){

        return questionBoardService.writeQuestionBoard(questionBoard);

    }


    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(path="/writeanswer",
            method = {RequestMethod.GET,RequestMethod.POST},
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE })
    public List<QuestionBoard> writeAnswerBoard(@RequestBody QuestionAnswer questionAnswer){

        return questionBoardService.writeAnswerBoard(questionAnswer);
    }

}
