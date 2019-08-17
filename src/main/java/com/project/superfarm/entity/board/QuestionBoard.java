package com.project.superfarm.entity.board;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="question_board")
@ToString @Setter @Getter
@DynamicInsert
@DynamicUpdate
public class QuestionBoard implements Serializable{

    @Id
    @Column(name="question_board_num",nullable = false,updatable = false,unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionBoardNum;

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
    @Where(clause = "answer_deleted = 'false'")
    @Fetch(FetchMode.SUBSELECT)
    private Set<QuestionAnswer> questionAnswer;


    public Long getQuestionBoardNum() {
        return questionBoardNum;
    }

    public void setQuestionBoardNum(Long questionBoardNum) {
        this.questionBoardNum = questionBoardNum;
    }

    public Long getProductBoardNum() {
        return productBoardNum;
    }

    public void setProductBoardNum(Long productBoardNum) {
        this.productBoardNum = productBoardNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestionBoardPassword() {
        return questionBoardPassword;
    }

    public void setQuestionBoardPassword(String questionBoardPassword) {
        this.questionBoardPassword = questionBoardPassword;
    }

    public String getQuestionBoardTitle() {
        return questionBoardTitle;
    }

    public void setQuestionBoardTitle(String questionBoardTitle) {
        this.questionBoardTitle = questionBoardTitle;
    }

    public String getQuestionBoardContent() {
        return questionBoardContent;
    }

    public void setQuestionBoardContent(String questionBoardContent) {
        this.questionBoardContent = questionBoardContent;
    }

    public Date getQuestionBoardRegdate() {
        return questionBoardRegdate;
    }

    public void setQuestionBoardRegdate(Date questionBoardRegdate) {
        this.questionBoardRegdate = questionBoardRegdate;
    }

    public String getQuestionBoardStatus() {
        return questionBoardStatus;
    }

    public void setQuestionBoardStatus(String questionBoardStatus) {
        this.questionBoardStatus = questionBoardStatus;
    }

    public String getQuestionBoardDeleted() {
        return questionBoardDeleted;
    }

    public void setQuestionBoardDeleted(String questionBoardDeleted) {
        this.questionBoardDeleted = questionBoardDeleted;
    }

    public Date getQuestionBoardDeleteDate() {
        return questionBoardDeleteDate;
    }

    public void setQuestionBoardDeleteDate(Date questionBoardDeleteDate) {
        this.questionBoardDeleteDate = questionBoardDeleteDate;
    }

    public Set<QuestionAnswer> getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(Set<QuestionAnswer> questionAnswer) {
        this.questionAnswer = questionAnswer;
    }
}
