package com.project.superfarm.entity.util;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "review_file")
@DynamicUpdate
@DynamicInsert
public class ReviewFiles {
    @Id
    @Column(name = "file_id")
    @Getter
    @Setter
    private int fileId;

    @Column(name = "file_origin_name")
    @Getter
    @Setter
    private String fileOriginName;

    @Column(name = "file_name")
    @Getter
    @Setter
    private String fileName;

    @Column(name = "file_size")
    @Getter
    @Setter
    private Long fileSize;

    @Column(name = "file_reg_dated", updatable = false, insertable = false)
    @Getter
    @Setter
    private Date fileRegDated;

    @Column(name = "file_upload_ip")
    @Getter
    @Setter
    private String fileUploadIp;

    @Column(name = "user_id")
    @Getter
    @Setter
    private String userId;

    @Column(name = "file_content_type")
    @Getter
    @Setter
    @Nullable
    private String fileContentType;

    @Column(name="review_board_num")
    @Getter
    @Setter
    private Long reviewBoardNum;


}
