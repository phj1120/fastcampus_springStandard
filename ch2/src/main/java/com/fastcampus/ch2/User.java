package com.fastcampus.ch2;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
	private String id;
	private String pwd;
	private String name;
	private String email;
//	birth �ʵ忡�� ���� ��
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date birth;
	private String[] hobby;
	private String[] sns;
}
