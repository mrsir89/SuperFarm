package com.project.superfarm.model;


import com.project.superfarm.entity.board.NoticeBoard;
import lombok.Data;

@Data
public class NoticeBoardModel {


    public NoticeBoard getNotice() {
        NoticeBoard noticeBoard = new NoticeBoard();

        return noticeBoard;
    }
}
