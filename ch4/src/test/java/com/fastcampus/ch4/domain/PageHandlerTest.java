package com.fastcampus.ch4.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class PageHandlerTest {

    @Test
    public void valueTest() {
        PageHandler ph1 = new PageHandler(Integer.valueOf(255), Integer.valueOf(26));
        ph1.print();
        System.out.println("\nph1 = " + ph1);

        PageHandler ph2 = new PageHandler(255, 11);
        ph2.print();
        System.out.println("\nph2 = " + ph2);

        PageHandler ph3 = new PageHandler(255, 1);
        ph3.print();
        System.out.println("\nph3 = " + ph3);

    }
}