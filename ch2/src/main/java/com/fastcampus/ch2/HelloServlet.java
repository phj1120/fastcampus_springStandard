package com.fastcampus.ch2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Hello.java 에 @RequestMapping("/hello")가 이미 있어서 변경
@WebServlet("/helloServlet")
public class HelloServlet extends HttpServlet {
	
//	서블릿이 초기화 될 때 자동으로 호출되는 메서드
//	1. 서블릿의 초기화 작업
	@Override
	public void init() throws ServletException {
		System.out.println("HelloServlet init() is called");
	}
	
//	2. 요청이 들어왔을 경우
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1. 입력		
//		2. 처리		
//		3. 출력		
		System.out.println("HelloServlet service() is called");
	}

//	서블릿이 내려올 때
//	리로드, 종료
//	3. 뒷정리 - 서블릿이 메모리에서 제거 될 때 서블릿 컨테이너에 의해서 자동 호출
	@Override
	public void destroy() {
		System.out.println("HelloServlet destroy() is called");
	}

	

}
