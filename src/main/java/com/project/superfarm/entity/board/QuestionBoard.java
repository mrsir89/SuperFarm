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




}
