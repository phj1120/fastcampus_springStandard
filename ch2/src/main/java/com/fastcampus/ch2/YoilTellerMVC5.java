package com.fastcampus.ch2;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

//����� �Է��ϸ� ���� �˷��ִ� ���α׷�
@Controller
public class YoilTellerMVC5 {

	@ExceptionHandler
	public String cathcer(Exception ex) {
		ex.printStackTrace();
		return "yoilError";
	}
	
	@RequestMapping("/getYoilMVC5") //http://localhost:8080/ch2/getYoilMVC5?year=2021&month=1&day=7
//	public String main(@ModelAttribute("myDate") MyDate date, Model model) throws Exception {	
//	public String main(@ModelAttribute MyDate date, Model model) throws Exception {	// key ���� ����
	public String main(MyDate date, Model model) throws Exception {	// @ModelAttribute ���� ����
		
//		1. ��ȿ�� �˻�
		if(!isValid(date))
			return "yoilError";
		
//		2. ���� ���
//		char yoil = getYoil(date);
		
//		3. Model�� �� ����
//		model.addAttribute("mtDate", date);
//		model.addAttribute("yoil", yoil);
				
//		4. View ȣ��
		return "yoil"; //WEB-INF/views/yoil.jsp
	}

	private boolean isValid(MyDate date) {
		int year = date.getYear();
		int month = date.getMonth();
		int day = date.getDay();
		
		if(year==-1 || month==-1 ||day==-1)
			return false;
		
		return (month>=1 && month<=12) && (day>=1 && day<= 31);
	}

	private @ModelAttribute("yoil") char getYoil(MyDate date) {
		int year = date.getYear();
		int month = date.getMonth();
		int day = date.getDay();
		
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		char yoil = " �Ͽ�ȭ�������".charAt(dayOfWeek);
		System.out.println(yoil);
		return yoil;
	}
}