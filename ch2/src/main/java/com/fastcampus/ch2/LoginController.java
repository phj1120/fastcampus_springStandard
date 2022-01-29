package com.fastcampus.ch2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
//		HttpServletRequest로 받아서 getSession으로 받아와도 되지만
//		이렇게 HttpSession 을 매개변수로 받아오면 스프링이 알아서 해 줌

//		1. 세션 종료
		session.invalidate();
//		2. 홈으로 이동
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login(
//			@CookieValue("id") String cookieId, 
//			@CookieValue("JSESSIONID") String sessionId,
			String toURL, String id, String pwd, 
			boolean rememberId, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
//		rememberId 가
//		boolean 일 경우 : true / false
//		String 일 경우  : on / null
		
//		String[] strArr = new String[2];
//		strArr[0] = "abc";
//		strArr[1] = "def";
//		
//		System.out.println(strArr);
		
//		1. id, pwd 확인
		if(!loginCheck(id, pwd)) {
//		    2.1 일치하지 않을 경우 loginForm 으로 이동
			String msg = URLEncoder.encode("id 또는 pwd 가 일치하지 않습니다.", "utf-8");
			return "redirect:/login/login?msg="+msg;
		}
		
//		2.2 일치할 경우 홈으로 이동
//		1. 쿠키 생성
		Cookie cookie = new Cookie("id", id);
		
//		1. 세션 객체 얻어오기
		HttpSession session = request.getSession();
//		2. 세션 객체에 id 저장
		session.setAttribute("id", id);
		
		System.out.println("[session id] : "+session.getAttribute("id"));
		
		if(rememberId) {
//			System.out.println("쿠키 생성");
//			쿠키 속성이 다르면 삭제가 안되네
//			cookie.setValue(id);
//			cookie.setDomain("localhost");
//			cookie.setPath("/");
			cookie.setMaxAge(60*60*24);
		} else {
//			2. 쿠키 삭제
//			System.out.println("쿠키 삭제");
			cookie.setMaxAge(0); 
		}
		
//		3. 쿠키 응답에 저장
		response.addCookie(cookie);
//		3. 홈으로 이동
		
		toURL = ("".equals(toURL)||toURL == null)? "/": toURL;
		System.out.println(toURL);
		return "redirect:"+toURL;
	}

	private boolean loginCheck(String id, String pwd) {
		return "asdf".equals(id) && "1234".equals(pwd); // 이렇게 하면 null 체크 안해줘도 됨
	}
}
