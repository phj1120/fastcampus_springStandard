package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hello {
	
	int iv = 10; // �ν��Ͻ� ����
	static int cv = 20; // static ����
	
	@RequestMapping("/hello")
	public void main() { // �ν��Ͻ� �޼��� cv, iv ��� ����
		System.out.println("Hello - instance");
		System.out.println(cv);
		System.out.println(iv);
	}
	
	public static void main2() { // static �޼��� cv ��� ����
		System.out.println("Hello - static");
		System.out.println(cv);
//		System.out.println(iv); // ����
	}
	
	@RequestMapping("/hello-private")
	private void main3() { // �ν��Ͻ� �޼��� cv, iv ��� ����
		System.out.println("Hello - private");
		System.out.println(cv);
		System.out.println(iv);
	}
	
}
