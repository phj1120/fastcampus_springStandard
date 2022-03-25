package com.fastcampus.ch4.domain;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class PageHandler {
    private int totalCount;
    private int pageSize;
    private int naviSize = 10;
    private int totalPage;
    private int page;
    private int beginPage;
    private int endPage;
    private boolean showPrev;
    private boolean showNext;

    public PageHandler(int totalCount, int page) {
        this(totalCount, page, 10);
    }

    public PageHandler(int totalCount, int page, int pageSize) {
        this.totalCount = totalCount;
        this.page = page;
        this.pageSize = pageSize;
        this.totalPage = (int) Math.ceil(totalCount / (double) pageSize);

        this.beginPage = ((page - 1) / naviSize) * naviSize + 1;
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



