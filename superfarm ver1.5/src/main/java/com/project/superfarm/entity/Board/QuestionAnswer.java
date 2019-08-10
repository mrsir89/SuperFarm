package com.project.superfarm.entity.Board;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.FilterDef;

import javax.persistence.*;
import java.util.Date;
@ToString
@Entity
@Table(name="question_answer")
@DynamicInsert
@DynamicUpdate @Getter @Setter
public class QuestionAnswer {

    @Id
    @Column(name = "answer_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerNum;

    @Column(name = "answer_date")
    @CreationTimestamp
    private Date answerDate;

    @Column(name = "answer_content")
    private String answerContent;

    @Column(name = "question_board_num")
    private Long questionBoardNum;

    @Column(name = "answer_writer")
    private String answerWriter;

    @Column(name="answer_deleted")
    private String answerDeleted;

    @Column(name = "answer_board_delete_date")
    private Date answerBoardDeletedDate;


}
