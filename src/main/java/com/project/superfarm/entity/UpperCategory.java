package com.project.superfarm.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="upper_category")
public class UpperCategory {

    @Id
    @Column(name="upper_code",nullable = false,updatable = false)
    private Integer upperCode;

    @Column(name="upper_title")
    private String upperTitle;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "upper_lower",
            joinColumns = @JoinColumn(name = "upper_code"),
            inverseJoinColumns = @JoinColumn(name = "lower_code")
    )
    private List<LowerCategory> lowerCategory;

    public Integer getUpperCode() {
        return upperCode;
    }

    public void setUpperCode(Integer upperCode) {
        this.upperCode = upperCode;
    }

    public String getUpperTitle() {
        return upperTitle;
    }

    public void setUpperTitle(String upperTitle) {
        this.upperTitle = upperTitle;
    }

    public List<LowerCategory> getLowerCategory() {
        return lowerCategory;
    }

    public void setLowerCategory(List<LowerCategory> lowerCategory) {
        this.lowerCategory = lowerCategory;
    }
}
