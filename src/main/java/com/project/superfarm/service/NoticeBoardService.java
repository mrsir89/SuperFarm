package com.project.superfarm.service;

import com.project.superfarm.entity.board.NoticeBoard;
import com.project.superfarm.model.NoticeBoardModel;
import com.project.superfarm.model.ResultItems;
import com.project.superfarm.repository.boardRepository.NoticeBoardRepository;
import com.project.superfarm.util.ExceptionList.UrlNotFountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NoticeBoardService {


    @Autowired
    private NoticeBoardRepository noticeBoardRepository;

    public Page<NoticeBoard> loadAllNoticeBoard(Pageable pageable){

        return noticeBoardRepository.findAllByNoticeDeletedOrderByNoticeNumDesc("false",pageable);
    }

    public void createNotice(NoticeBoardModel noticeBoardModel) {

            NoticeBoard noticeBoard = noticeBoardModel.getNotice();
            NoticeBoard returnNoticeBoard = noticeBoardRepository.save(noticeBoard);
            if(returnNoticeBoard != null){
                return;
            }else{
                throw new UrlNotFountException();
            }

    }
}
