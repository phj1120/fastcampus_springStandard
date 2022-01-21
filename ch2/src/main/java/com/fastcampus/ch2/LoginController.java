package com.fastcampus.ch2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
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
//		HttpServletRequest�� �޾Ƽ� getSession���� �޾ƿ͵� ������
//		�̷��� HttpSession �� �Ű������� �޾ƿ��� �������� �˾Ƽ� �� ��

//		1. ���� ����
		session.invalidate();
//		2. Ȩ���� �̵�
		return "redirect:/";
	}
	
	
	@PostMapping("/login")
	public String login(String id, String pwd, boolean rememberId, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
//		rememberId ��
//		boolean �� ��� : true / false
//		String �� ���  : on / null
		
//		1. id, pwd Ȯ��
		if(!loginCheck(id, pwd)) {
//		    2.1 ��ġ���� ���� ��� loginForm ���� �̵�
			String msg = URLEncoder.encode("id �Ǵ� pwd �� ��ġ���� �ʽ��ϴ�.", "utf-8");
			return "redirect:/login/login?msg="+msg;
		}
		
//		2.2 ��ġ�� ��� Ȩ���� �̵�
//		1. ��Ű ����
		Cookie cookie = new Cookie("id", id);
		
//		1. ���� ��ü ������
		HttpSession session = request.getSession();
//		2. ���� ��ü�� id ����
		session.setAttribute("id", id);
		
		if(rememberId) {
			System.out.println("��Ű ����");
//			��Ű �Ӽ��� �ٸ��� ������ �ȵǳ�
//			cookie.setValue(id);
//			cookie.setDomain("localhost");
//			cookie.setPath("/");
			cookie.setMaxAge(60*60*24);
		} else {
//			2. ��Ű ����
			System.out.println("��Ű ����");
			cookie.setMaxAge(0); 
		}
		
//		3. ��Ű ���信 ����
		response.addCookie(cookie);

//		3. Ȩ���� �̵�
		return "redirect:/";
	}

	private boolean loginCheck(String id, String pwd) {
		return "asdf".equals(id) && "1234".equals(pwd); // �̷��� �ϸ� null üũ �����൵ ��
	}
}
