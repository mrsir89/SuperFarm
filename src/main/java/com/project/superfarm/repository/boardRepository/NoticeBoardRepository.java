package com.project.superfarm.repository.boardRepository;


import com.project.superfarm.entity.board.NoticeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeBoardRepository extends JpaRepository<NoticeBoard,Long> {

    Page<NoticeBoard> findAllByNoticeDeletedOrderByNoticeNumDesc(String deleted, Pageable pageable);

}
