package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hello {
	
	int iv = 10; // 인스턴스 변수
	static int cv = 20; // static 변수
	
	@RequestMapping("/hello")
	public void main() { // 인스턴스 메서드 cv, iv 사용 가능
		System.out.println("Hello - instance");
		System.out.println(cv);
		System.out.println(iv);
	}
	
	public static void main2() { // static 메서드 cv 사용 가능
		System.out.println("Hello - static");
		System.out.println(cv);
//		System.out.println(iv); // 에러
	}
	
	@RequestMapping("/hello-private")
	private void main3() { // 인스턴스 메서드 cv, iv 사용 가능
		System.out.println("Hello - private");
		System.out.println(cv);
		System.out.println(iv);
	}
	
}
