package com.fastcampus.ch4.domain;

import lombok.ToString;

@ToString
public class PageHandler {
    int totalCount;
    int pageSize;
    int naviSize = 10;
    int totalPage;
    int page;
    int beginPage;
    int endPage;
    boolean showPrev;
    boolean showNext;

    public PageHandler(int totalCount, int page) {
        this(totalCount, page, 10);
    }

    public PageHandler(int totalCount, int page, int pageSize) {
        this.totalCount = totalCount;
        this.page = page;
        this.pageSize = pageSize;
        this.totalPage = (int) Math.ceil(totalCount / (double) pageSize);

        this.beginPage = (page / naviSize) * naviSize + 1;
        this.endPage = Math.min(beginPage + naviSize - 1, totalPage);
        this.showPrev = page != 1;
        this.showNext = page != totalPage;
    }

    public void print() {
        if (showPrev) {
            System.out.print("[prev] ");
        }

        for (int i = beginPage; i <= endPage; i++) {
            System.out.printf("%d ", i);
        }

        if (showNext) {
            System.out.print("[next]");
        }
    }
}



