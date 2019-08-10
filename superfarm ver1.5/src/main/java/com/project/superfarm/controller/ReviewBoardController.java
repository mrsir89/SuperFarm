package com.project.superfarm.controller;


import com.project.superfarm.entity.Board.ReviewBoard;
import com.project.superfarm.service.ReviewBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;



/*

    @ Review Board

    @loadAllReviewBoard        /review/all
    전체 Load

    @loadFromProductBoard      /review/code
    formdata에 productBoardCode 변수 이름으로 보내면 됨
    productBoard 별 Load

    @writeReviewBoard          /review/write
    review 작성

    Test 양식
    {
	"productBoardNum":"프로덕트 보드 번호",
	"customerId":"작성자 ID",
	"reviewBoardImg":"리뷰 이미지 주소 null 가능",
	"reviewBoardTitle":" 리뷰 제목 ",
	"reviewBoardContent":"리뷰 내용",
	"reviewBoardRating":" 별점 0~5까지"
    }


 */

@RestController
@RequestMapping("/review")
public class ReviewBoardController {


    @Autowired
    private ReviewBoardService reviewBoardService;

    @PreAuthorize("hasRole('guest')")
    @RequestMapping(value = "/all",
    method={RequestMethod.POST,RequestMethod.GET},
    produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
                MediaType.APPLICATION_ATOM_XML_VALUE})
    public List<ReviewBoard>loadAllReviewBoard(){


        return reviewBoardService.loadAllReviewBoard();
    }

    @PreAuthorize("hasRole('guest')")
    @RequestMapping(value = "/code",
    method = {RequestMethod.POST,RequestMethod.GET},
    produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
                MediaType.APPLICATION_ATOM_XML_VALUE})
    public List<ReviewBoard> loadFromProductBoard(Long productBoardCode){

        return reviewBoardService.loadFromProductBoard(productBoardCode);
    }

    @PreAuthorize("hasRole('guest')")
    @RequestMapping(value="/write",
    method = {RequestMethod.POST,RequestMethod.GET},
    produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
                MediaType.APPLICATION_ATOM_XML_VALUE})
    public List<ReviewBoard> writeReviewBoard(@RequestBody ReviewBoard reviewBoard){

        return reviewBoardService.writeReviewBoard(reviewBoard);
    }

    @PreAuthorize("hasRole('guest')")
    @RequestMapping(value="/delete",
    method = {RequestMethod.POST,RequestMethod.GET},
    produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
                MediaType.APPLICATION_ATOM_XML_VALUE})
    public String deleteReviewBoard(@RequestParam Long reviewBoardNum){

        return reviewBoardService.deleteReviewBoard(reviewBoardNum);
    }

}
