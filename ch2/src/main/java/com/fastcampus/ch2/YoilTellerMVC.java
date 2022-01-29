package com.fastcampus.ch2;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//����� �Է��ϸ� ���� �˷��ִ� ���α׷�
@Controller
public class YoilTellerMVC {

	
//	String ��ȯ
	@RequestMapping("/getYoilMVC") //http://localhost:8080/ch2/getYoilMVC?year=2021&month=1&day=7
	public String main(@RequestParam(required=true) int year, 
			@RequestParam(required=true) int month, 
			@RequestParam(required=true) int day, Model model) throws Exception {	

//		1. ��ȿ�� �˻�
		if(!isValid(year, month, day))
			return "yoilError";
		
//		2. ���� ���
		char yoil = getYoil(year, month, day);
		
//		3. Model�� �� ����
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("yoil", yoil);
		
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
		System.out.println(yoil);
		
//		3. View ȣ��
		return "yoil"; //WEB-INF/views/yoil.jsp
	}
//	ModelAndView ��ȯ
//	@RequestMapping("/getYoilMVC") //http://localhost:8080/ch2/getYoilMVC?year=2021&month=1&day=7
//	public ModelAndView main(int year, int month, int day) throws Exception {	
////		1. ��ȿ�� �˻�
////		if(!isValid(year, month, day))
////			return "yoilError";
////		
//		ModelAndView mv = new ModelAndView();
//		
////		2. ���� ���
//		char yoil = getYoil(year, month, day);
//		
////		3. Model�� �� ����
//		mv.addObject("year", year);
//		mv.addObject("month", month);
//		mv.addObject("day", day);
//		mv.addObject("yoil", yoil);
//		
//		System.out.println(year);
//		System.out.println(month);
//		System.out.println(day);
//		System.out.println(yoil);
//		
//		mv.setViewName("yoil");	
//		
////		3. View ȣ��
//		return mv; //WEB-INF/views/yoil.jsp
//	}

	private boolean isValid(int year, int month, int day) {
		return true;
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		char yoil = " �Ͽ�ȭ�������".charAt(dayOfWeek);
		System.out.println(yoil);
		return yoil;
	}

	
}