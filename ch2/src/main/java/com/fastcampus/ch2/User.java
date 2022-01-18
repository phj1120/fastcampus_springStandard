package com.fastcampus.ch2;

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
	private String birth;
	private String sns;
}
