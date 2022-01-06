package com.fastcampus.ch2;

import java.lang.reflect.Method;

public class PrivateMethodCall {

	public static void main(String[] args) throws Exception {
//		Hello hello = new Hello();
//		hello.main3();
		
//		Reflection API
//		Hello 클래스의 Class객체(클래스의 정보를 담고 있는 객체)를 얻어 옴
//		클래스에대한 정보가 있으므로 모든지 다 할 수 있음
		Class helloClass = Class.forName("com.fastcampus.ch2.Hello");
		Hello hello = (Hello)helloClass.newInstance();
		Method main = helloClass.getDeclaredMethod("main3");
		main.setAccessible(true); // private 인 main을 호출 가능하도록
		main.invoke(hello); // hello.main()
	}
}
