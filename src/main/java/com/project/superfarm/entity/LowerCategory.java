package com.project.superfarm.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="lower_category")
public class LowerCategory {

    @Id
    @Column(name ="lower_code",updatable = false,nullable = false)
    private Integer lowerCode;

    @Column(name="lower_title")
    private String lowerTitle;




    public Integer getLowerCode() {
        return lowerCode;
    }

    public void setLowerCode(Integer lowerCode) {
        this.lowerCode = lowerCode;
    }

    public String getLowerTitle() {
        return lowerTitle;
    }

    public void setLowerTitle(String lowerTitle) {
        this.lowerTitle = lowerTitle;
    }
}
