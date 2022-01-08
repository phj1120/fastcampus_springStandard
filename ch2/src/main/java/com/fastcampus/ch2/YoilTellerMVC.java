package com.fastcampus.ch2;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//년월일 입력하면 요일 알려주는 프로그램
@Controller
public class YoilTellerMVC {

	@RequestMapping("/getYoilMVC") //http://localhost:8080/ch2/getYoilMVC?year=2021&month=1&day=7
	public String main(int year, int month, int day, Model model) throws Exception {	
//		1. 유효성 검사
		if(!isValid(year, month, day))
			return "yoilError";
		
//		2. 요일 계산
		char yoil = getYoil(year, month, day);
		
//		3. Model에 값 저장
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("yoil", yoil);
		
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
		System.out.println(yoil);
		
//		3. View 호출
		return "yoil"; //WEB-INF/views/yoil.jsp
	}

	private boolean isValid(int year, int month, int day) {
		return true;
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		char yoil = " 일월화수목금토".charAt(dayOfWeek);
		System.out.println(yoil);
		return yoil;
	}
}