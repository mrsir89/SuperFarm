package com.project.superfarm.controller;


import com.project.superfarm.entity.board.NoticeBoard;
import com.project.superfarm.model.ResultItems;
import com.project.superfarm.service.NoticeBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/notice")
public class NoticeBoardController {


    @Autowired
    private NoticeBoardService noticeBoardService;

    @PreAuthorize("hasAnyRole('GUEST','CUSTOMER','ADMIN')")
    @RequestMapping(
            method= RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
            })
    public ResultItems<NoticeBoard> loadAllNotice(
            @RequestParam(name="page",defaultValue ="1", required = false)Integer page,
            @RequestParam(name="size",defaultValue = "10", required =false)Integer size,
            @RequestParam(name = "sort", defaultValue = "faqBoardNum", required = false) String sort){
        Pageable pageable = PageRequest.of(page-1,size, Sort.by(sort).descending());

        Page<NoticeBoard> noticeBoards = noticeBoardService.loadAllNoticeBoard(pageable);

        return new ResultItems<NoticeBoard>(noticeBoards.getContent(),page,size,noticeBoards.getTotalElements(),
                noticeBoards.getTotalPages(),noticeBoards.hasNext());
    }
}
