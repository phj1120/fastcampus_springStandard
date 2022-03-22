package com.fastcampus.ch4.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class PageHandlerTest {

    @Test
    public void valueTest() {

        PageHandler ph = new PageHandler(255, 26);
        ph.print();
        System.out.println("\nph = " + ph);
    }
}