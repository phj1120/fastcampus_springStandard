package com.fastcampus.ch4.commons;

import com.fastcampus.ch4.commons.aop.Calc;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.annotation.Aspect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
@Log4j
@Aspect
public class AopTests {

    @Autowired
    Calc calc;

    @Test
    public void beforeTest() {
        int a = 1, b = 2;
        int plus = calc.plus(a, b);
        int minus = calc.minus(a, b);
        System.out.println("plus = " + plus);
        System.out.println("minus = " + minus);
    }
}
