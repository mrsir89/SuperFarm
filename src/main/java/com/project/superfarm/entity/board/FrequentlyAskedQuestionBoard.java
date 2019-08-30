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
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "faq_board")
public class FrequentlyAskedQuestionBoard {

    @Id
    @Column(name = "faq_board_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long faqBoardNum;

    @Column(name = "faq_writer_num")
    private String noticeTitle;

    @Column(name = "faq_question")
    private String faqQuestion;

    @Column(name = "faq_answer")
    private String faqAnswer;

    @Column(name = "faq_write_date")
    @CreationTimestamp
    private Date faqeWriteDate;

    @Column(name = "faq_edit_date")
    @UpdateTimestamp
    private Date faqEditDate;

    @Column(name = "faq_img")
    private String faqImg;

    @Column(name = "faq_deleted")
    private String faqDeleted;

}