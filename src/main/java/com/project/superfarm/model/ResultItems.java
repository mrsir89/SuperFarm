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


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public Long getBoardNum() {
        return boardNum;
    }

    public void setBoardNum(Long boardNum) {
        this.boardNum = boardNum;
    }
}
