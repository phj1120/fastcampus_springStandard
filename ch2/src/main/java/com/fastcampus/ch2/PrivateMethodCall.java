package com.fastcampus.ch2;

import java.lang.reflect.Method;

public class PrivateMethodCall {

	public static void main(String[] args) throws Exception {
//		Hello hello = new Hello();
//		hello.main3();
		
//		Reflection API
//		Hello Ŭ������ Class��ü(Ŭ������ ������ ��� �ִ� ��ü)�� ��� ��
//		Ŭ���������� ������ �����Ƿ� ����� �� �� �� ����
		Class helloClass = Class.forName("com.fastcampus.ch2.Hello");
		Hello hello = (Hello)helloClass.newInstance();
		Method main = helloClass.getDeclaredMethod("main3");
		main.setAccessible(true); // private �� main�� ȣ�� �����ϵ���
		main.invoke(hello); // hello.main()
	}
}
