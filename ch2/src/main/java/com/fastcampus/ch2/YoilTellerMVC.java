package com.fastcampus.ch2;

import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//년월일 입력하면 요일 알려주는 프로그램
@Controller
public class YoilTellerMVC {
	
//	String 반환 
//	어떤 view를 이용해 결과를 보여줄지
//  view 의 이름을 디스패쳐서블릿에 반환
//	모델은 디스패쳐 서블릿에서 생성된 것으로 따로 반환해줄 필요 없음
	@RequestMapping("/getYoilMVC") //http://localhost:8080/ch2/getYoilMVC?year=2021&month=1&day=7
//	public String main(@RequestParam(required=true) int year, 
//			@RequestParam(required=true) int month, 
//			@RequestParam(required=true) int day, Model model) throws Exception {	
	public String main(int year, int month, int day, Model model) throws Exception{
//		1. 유효성 검사
		if(!isValid(year, month, day))
			return "yoilError";
		
//		2. 요일 계산
		char yoil = getYoil(year, month, day);
		
//		3. Model에 값 저장 
//		Controller 가 작업한 결과를 DispacherServlet을 통해 view 에게 넘겨주기 위해
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("yoil", yoil);
		
//		3. View 호출
		return "yoil"; //WEB-INF/views/yoil.jsp
	}
	
//	ModelAndView 반환
//	@RequestMapping("/getYoilMVC") //http://localhost:8080/ch2/getYoilMVC?year=2021&month=1&day=7
//	public ModelAndView main(int year, int month, int day) throws Exception {	
////		1. 유효성 검사
////		if(!isValid(year, month, day))
////			return "yoilError";
////		
//		ModelAndView mv = new ModelAndView();
//		
////		2. 요일 계산
//		char yoil = getYoil(year, month, day);
//		
////		3. Model에 값 저장
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
////		3. View 호출
//		return mv; //WEB-INF/views/yoil.jsp
//	}

	private boolean isValid(int year, int month, int day) {
		return true;
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		char yoil = " 일월화수목금토".charAt(dayOfWeek);
//		System.out.println(yoil);
		return yoil;
	}

	
}