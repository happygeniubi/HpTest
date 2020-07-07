package com.happyge.empl.utils;

import java.util.List;

public class PageHolder<T> {

    private int curPage;// 当前页
    private int pageNum;// 每页条数
    private int totalCount;// 总记录数
    private int totalPage;// 总页数
    private int offset;// 起始记录数
    private List<T> data;// 数据
    private int[] navigatepageNums;
    private int navigateFirstPage;
    private int navigateLastPage;

    public PageHolder(int curPage, int pageNum, int totalCount) {
        this.curPage = curPage;
        this.pageNum = pageNum;
        this.totalCount = totalCount;
        init();
    }

    private void init() {
        processTotalPage();
        processOffset();
    }



    private void processTotalPage() {
        this.totalPage = (totalCount % pageNum) == 0 ? totalCount / pageNum : totalCount / pageNum + 1;
    }

    private void processOffset() {
        this.offset = (curPage - 1) * pageNum;
    }

    public int getCurPage() {
        return curPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getOffset() {
        return offset;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
