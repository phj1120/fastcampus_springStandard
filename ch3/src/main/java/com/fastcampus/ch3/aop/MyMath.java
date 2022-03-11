package com.fastcampus.ch3.aop;

// 부가 기능을 적용할 target
// bean 으로 등록

import org.springframework.stereotype.Component;

@Component
public class MyMath {
    public int add(int a, int b) {
        return a + b;
    }


    public int add(int a, int b, int c) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }
}
