package com.project.superfarm.entity.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@ToString
@Entity
@DynamicInsert
@DynamicUpdate @Getter @Setter
@Table(name="notice_board")
public class NoticeBoard {

        @Id
        @Column(name="notice_num")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long noticeNum;

        @Column(name="notice_title")
        private String noticeTitle;

        @Column(name="notice_writer_num")
        private String noticeWriterNum;

        @Column(name="notice_writer_name")
        private String noticeWriterName;

        @Column(name="notice_write_date")
        @CreationTimestamp
        private Date noticeWriteDate;

        @Column(name="notice_edit_date")
        @UpdateTimestamp
        private Date noticeEditDate;

        @Column(name="notice_content")
        private String noticeContent;

        @Column(name="notice_img")
        private String noticeImg;

        @Column(name="notice_deleted")
        private String noticeDeleted;

        @Column(name="notice_views")
        private String noticeViews;

    }

