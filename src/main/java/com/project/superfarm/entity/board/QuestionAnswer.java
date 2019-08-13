package com.project.superfarm.entity.board;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@ToString @Getter @Setter
@Entity
@Table(name="question_answer")
@DynamicUpdate
@DynamicInsert
public class QuestionAnswer implements Serializable {

    @Id
    @Column(name = "answer_num",updatable = false,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerNum;

    @Column(name = "answer_date",updatable = true)
    @CreationTimestamp
    private Date answerDate;

    @Column(name = "answer_content")
    private String answerContent;

    @Column(name = "question_board_num",updatable = false,nullable = false)
    private Long questionBoardNum;

    @Column(name = "answer_writer")
    private String answerWriter;

    @Column(name="answer_deleted")
    private String answerDeleted;

    @Column(name = "answer_board_delete_date")
    private Date answerBoardDeletedDate;


}
