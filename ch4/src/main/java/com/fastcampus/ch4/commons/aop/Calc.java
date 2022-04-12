package com.fastcampus.ch4.commons.aop;

import org.springframework.stereotype.Component;

@Component
public class Calc {

    public int plus(int a, int b) {
        return a + b;
    }

    public int minus(int a, int b) {
        return a - b;
    }
}
