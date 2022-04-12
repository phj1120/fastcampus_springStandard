package com.fastcampus.ch4.commons.aop;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Aspect
@Component
public class TestAop {
    @Before("execution(* com.fastcampus.ch4.commons.aop.* (*))")
    public void aopExecution(){
        System.out.println("[aopExecution]");
    }

    @Around("within(com.fastcampus.ch4.commons.aop.*)")
    public void aopWithin() {
        System.out.println("[aopWithin]");
    }


    @Before("execution(* com.fastcampus.ch4.controller.BoardController.* (request, response, *))")
    public void validateAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!loginCheck(request)) {
            System.out.println("로그인 실패");
            response.sendRedirect("redirect:/login/login?toURL=" + request.getRequestURL());
        }
        System.out.println("로그인 성공 id = " + request.getSession().getAttribute("id"));
    }

    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("id") != null;
    }
}
