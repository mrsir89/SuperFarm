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
import java.util.List;

@Entity
@Table(name="question_board")
@ToString @Setter @Getter
@DynamicInsert
@DynamicUpdate
public class QuestionBoardAll implements Serializable{

    @Id
    @Column(name="question_board_num",nullable = false,updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questBoardNum;

    @Column(name="product_board_num")
    private Long productBoardNum;

    @Column(name="user_id")
    private String userId;

    @Column(name="question_board_password")
    private String questionBoardPassword;

    @Column(name="question_board_title")
    private String questionBoardTitle;

    @Column(name="question_board_content")
    private String questionBoardContent;

    @Column(name="question_board_regdate")
    @CreationTimestamp
    private Date questionBoardRegdate;

    @Column(name="question_board_status")
    private String questionBoardStatus;

    @Column(name="question_board_deleted")
    private String questionBoardDeleted;

    @Column(name="question_board_delete_date")
    private Date questionBoardDeleteDate;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="question_board_num", referencedColumnName ="question_board_num")
    private List<QuestionAnswer> questionAnswer;

    public List<QuestionAnswer> getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(List<QuestionAnswer> questionAnswer) {

        this.questionAnswer = questionAnswer;

    }






}
