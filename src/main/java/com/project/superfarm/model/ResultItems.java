package com.project.superfarm.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;

@Setter
@Getter
public class ResultItems<T extends Object> implements Serializable {

    private Collection<T> items;

    private int page;

    private int size;

    private long totalCount;

    private int totalPage;

    private boolean hasNext;

    private Long boardNum;

    public ResultItems(){

    }

    public ResultItems(Collection<T> items, int page, int size, long totalCount,int totalPage,boolean hasNext) {
        this.items = items;
        this.page = page;
        this.size = size;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.hasNext = hasNext;
    }

    public Collection<T> getItems() {
        return items;
    }

    public void setItems(Collection<T> items) {
        this.items = items;
    }


}
