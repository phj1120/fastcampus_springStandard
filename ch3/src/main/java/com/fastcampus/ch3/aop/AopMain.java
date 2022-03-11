package com.fastcampus.ch3.aop;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AopMain {
    public static void main(String[] args) throws Exception {
        MyAdvice myAdvice = new MyAdvice();
        Class myClass = Class.forName("com.fastcampus.ch3.aop.MyClass");
        Object obj = myClass.newInstance();

        for (Method m : myClass.getDeclaredMethods()) {
            myAdvice.invoke(m, obj, null);
        }

    }
}

class MyAdvice{
    // a로 시작하는 것
    Pattern p = Pattern.compile("a.*");
    boolean matches(Method m) {
        Matcher matcher = p.matcher(m.getName());
        return matcher.matches();
    }

    void invoke(Method m, Object obj, Object... args) throws Exception {
        if(m.getAnnotation(Transactional.class) != null)
//        if(matches(m))
            System.out.println("[before]");

        m.invoke(obj, args);

        if(m.getAnnotation(Transactional.class) != null)
//        if(matches(m))
            System.out.println("[after]");
    }
}


class MyClass {
//  예시로 Transactional 사용
    @Transactional
    void aaa(){
        System.out.println("MyClass.aaa");
    }

    void aaa2() {
        System.out.println("MyClass.aaa2");
    }

    void bbb() {
        System.out.println("MyClass.bbb");
    }
}